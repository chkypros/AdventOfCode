import src.aoc_template.solution as solution

def _increasing_safe_step_filter(first: int, second: int): return first < second <= (first + 3)

def _decreasing_safe_step_filter(first: int, second: int): return (first - 3) <= second < first

def _report_is_safe(line: str) -> bool:
    levels = list(map(int, line.split()))
    if levels[0] < levels[1]:
        step_filter = _increasing_safe_step_filter
    else:
        step_filter = _decreasing_safe_step_filter

    for i in range(1, len(levels)):
        if not (step_filter(levels[i-1], levels[i])): return False
    return True

class RedNosedReports(solution.AbstractSolution):
    def solve_part_one(self, input_lines: list[str]) -> object:
        safe_reports = [report for report in input_lines if _report_is_safe(report)]
        return len(safe_reports)
