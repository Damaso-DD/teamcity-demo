// .teamcity/settings.kts
import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.dockerSupport
import jetbrains.buildServer.configs.kotlin.buildSteps.script

version = "2025.03"

project {
    id("TeamCityDemo")
    name = "TeamCity Demo - From Cost Center to Innovation Engine"

    buildType {
        id("BuildAndTest")
        name = "1 - Build, Test, and Analyze"

        vcs {
            root(DslContext.settingsRoot)
        }

        steps {
            // Step 1: Install Dependencies
            script {
                name = "Install Python Dependencies"
                scriptContent = "pip install -r requirements.txt"
            }
            // Step 2: Run Tests, capture results and coverage
            script {
                name = "Run Tests and Generate Reports"
                scriptContent = """
                    # Generate JUnit XML for test reports and Cobertura XML for coverage
                    pytest --junitxml=test-results.xml --cov=src --cov-report=xml:coverage.xml tests/
                """.trimIndent()
            }
            // Step 3: Build a deployable artifact (a Docker image)
            script {
                name = "Build Docker Image"
                scriptContent = "docker build -t my-app:%build.number% ."
            }
        }

        features {
            // Feature to parse test reports
            xmlReport {
                reportType = XmlReport.XmlReportType.JUNIT
                rules = "test-results.xml"
            }
            // Feature to parse code coverage reports
            coverage {
                // TeamCity's JaCoCo parser works well with Cobertura format from pytest-cov
                engine = jacoco {
                    coverageReportType = JacocoCoverageReport.ReportType.XML
                    classFiles = "**/*.py"
                }
                rules = "+:src"
                coverageReportPath = "coverage.xml"
            }
            // Feature to enable Docker integration
            dockerRegistry {
                cleanupPushedImages = true
            }
        }
    }

    buildType {
        id("DeployToStaging")
        name = "2 - Deploy to Staging"

        triggers {
            finishBuildTrigger {
                buildType = "${BuildAndTest.id}"
                successfulOnly = true
                branchFilter = "+:refs/heads/main"
            }
        }
        
        steps {
            script {
                name = "Simulate Deployment to Staging"
                scriptContent = """
                    echo "Deploying application version %dep.TeamCityDemo_BuildAndTest.build.number% to Staging"
                    echo "Deployment to Staging successful!"
                """.trimIndent()
            }
        }

        dependencies {
            snapshot(BuildAndTest) {}
        }
    }
}