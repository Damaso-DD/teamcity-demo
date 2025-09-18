# tests/test_calculator.py
import pytest
import random
from src.calculator import Calculator

@pytest.fixture
def calculator():
    """Returns a Calculator instance."""
    return Calculator()

def test_add(calculator):
    """Test the add function."""
    assert calculator.add(2, 3) == 5
    assert calculator.add(-1, 1) == 0

def test_subtract(calculator):
    """Test the subtract function."""
    assert calculator.subtract(10, 5) == 5

def test_divide(calculator):
    """Test the divide function."""
    assert calculator.divide(10, 2) == 5
    with pytest.raises(ValueError):
        calculator.divide(5, 0)

# This test will demonstrate bottleneck diagnosis
def test_slow_operation(calculator):
    """
    This test will show up in duration reports as a bottleneck.
    """
    assert calculator.slow_operation(duration_seconds=3) is True

# This test will demonstrate flaky test detection
def test_flaky_behavior(calculator):
    """
    This test is designed to fail intermittently (~20% of the time).
    TeamCity will detect and flag this as a flaky test.
    """
    assert random.random() > 0.2 # This will fail about 20% of the time

# To showcase improving code coverage, you will later add this test:
# def test_power(calculator):
#     """Test the power function."""
#     assert calculator.power(2, 3) == 8