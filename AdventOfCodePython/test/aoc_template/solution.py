import unittest

class AbstractSolutionTest(unittest.TestCase):
    def test_part_one(self):
        if hasattr(self, 'EXPECTED_ANSWER_PART_ONE'):
            assert self.EXPECTED_ANSWER_PART_ONE == -1
