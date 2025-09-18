# TeamCity Showcase Repository

This repository is a simple Python project designed to demonstrate key CI/CD metrics and features within TeamCity for the article "From Cost Center to Innovation Engine."

```
teamcity-demo/
├── .teamcity/
│   └── settings.kts
├── src/
│   └── calculator.py
├── tests/
│   └── test_calculator.py
├── .gitignore
├── Dockerfile
├── requirements.txt
└── README.md
```

## How to Showcase TeamCity Features

### 🎯 Foundation – Are we stable, and is our quality improving?

1.  **Build & Test Stability:** After running the `BuildAndTest` pipeline a few times, go to the project dashboard. You will see the **Success Rate** metric. Intentionally introduce a syntax error in `calculator.py` and commit it. The build will fail, and you can show how the success rate drops, providing a clear signal of instability.
2.  **Test Pass Rate:** Navigate to the **Tests** tab of the `BuildAndTest` build configuration. This dashboard shows the pass rate. Because of `test_flaky_behavior`, you will see this rate fluctuate.
3.  **Code Quality:** In a finished build's overview, click the **Code Coverage** tab. Initially, the coverage will be low because the `power()` method is not tested. To show improvement, uncomment the `test_power` function in `tests/test_calculator.py`, commit, and run the build again. You can then demonstrate the code coverage metric increasing, a tangible sign of improving quality.

### 🚀 Velocity – Are we shipping faster?

1.  **Deployment Frequency:** The `DeployToStaging` build runs automatically after every successful build on the main branch. The frequency of this build running is your **Deployment Frequency**. You can show this on the charts or statistics page in TeamCity.
2.  **Lead Time for Changes:** This can be demonstrated by looking at the time between a commit and when the `DeployToStaging` build successfully finishes.
3.  **Build Duration Trends:** On the **Statistics** tab for the `BuildAndTest` configuration, you can show the **Build Duration** graph. This is where you would spot if your build process is getting slower over time.

### 🔍 Diagnosis – Where are our bottlenecks?

1.  **Test Duration Trends:** Go to the **Tests** tab. You can sort tests by duration. The `test_slow_operation` will always appear at the top, immediately identifying it as a bottleneck in your test suite.
2.  **Flaky Test Detection:** After a few runs where `test_flaky_behavior` randomly fails and passes, TeamCity will automatically flag it. A "flaky test" icon will appear next to it in the test results, and it will be highlighted on the build overview page. This powerfully demonstrates how TeamCity pinpoints unreliable tests that erode trust in the pipeline.
3.  **In-depth Reports:** Click on any failed test to see a detailed report, including its history, stack trace, and duration statistics, empowering developers to diagnose issues quickly.
