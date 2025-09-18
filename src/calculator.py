# src/calculator.py
import time

class Calculator:
    """A simple calculator class."""

    def add(self, a, b):
        """Returns the sum of two numbers."""
        return a + b

    def subtract(self, a, b):
        """Returns the difference of two numbers."""
        return a - b

    def multiply(self, a, b):
        """Returns the product of two numbers."""
        return a * b

    def divide(self, a, b):
        """
        Returns the division of two numbers.
        Raises ValueError on division by zero.
        """
        if b == 0:
            raise ValueError("Cannot divide by zero")
        return a / b

    def power(self, a, b):
        """
        A function that we will initially forget to test
        to demonstrate code coverage metrics.
        """
        return a ** b

    def slow_operation(self, duration_seconds=2):
        """
        A deliberately slow function to simulate a bottleneck
        that TeamCity can help diagnose.
        """
        print(f"Performing a slow operation for {duration_seconds} seconds...")
        time.sleep(duration_seconds)
        print("Slow operation complete.")
        return True