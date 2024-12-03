import src.aoc_template.solution as solution

def _increasing_safe_step_filter(first: int, second: int): return first < second <= (first + 3)

def _decreasing_safe_step_filter(first: int, second: int): return (first - 3) <= second < first

def _report_is_safe(report: str) -> bool:
    levels = list(map(int, report.split()))
    if levels[0] < levels[1]:
        step_filter = _increasing_safe_step_filter
    else:
        step_filter = _decreasing_safe_step_filter

    for i in range(1, len(levels)):
        if not (step_filter(levels[i-1], levels[i])): return False
    return True

def _report_is_safe_with_dampener(report: str) -> bool:
    levels = list(map(int, report.split()))
    dampened_index = None

    num_levels = len(levels)
    mid_level_index = int(num_levels / 2)
    is_first_step_incrementing = levels[0] < levels[1]
    is_middle_step_incrementing = levels[mid_level_index - 1] < levels[mid_level_index]
    is_last_step_incrementing = levels[num_levels - 2] < levels[num_levels - 1]

    if is_middle_step_incrementing == is_last_step_incrementing != is_first_step_incrementing:
        dampened_index = 0
        previous = levels[1]
        i = 2
        if is_middle_step_incrementing:
            step_filter = _increasing_safe_step_filter
        else:
            step_filter = _decreasing_safe_step_filter
    else:
        i = 1
        previous = levels[0]
        if is_first_step_incrementing:
            step_filter = _increasing_safe_step_filter
        else:
            step_filter = _decreasing_safe_step_filter

    while i < num_levels:
        if not step_filter(previous, levels[i]):
            if dampened_index is None:
                dampened_index = i
            else:
                return False
        else:
            previous = levels[i]

        i += 1

    return True

class RedNosedReports(solution.AbstractSolution):
    def solve_part_one(self, input_lines: list[str]) -> object:
        safe_reports = [report for report in input_lines if _report_is_safe(report)]
        return len(safe_reports)

    def solve_part_two(self, input_lines: list[str]) -> object:
        safe_reports = [report for report in input_lines if _report_is_safe_with_dampener(report)]
        return len(safe_reports)
