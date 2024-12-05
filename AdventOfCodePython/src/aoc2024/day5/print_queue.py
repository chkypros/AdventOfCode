import src.aoc_template.solution as solution

def _parse_input(input_lines: list[str]) -> tuple[list[tuple[str,str]],list[list[str]]]:
    rules: list[tuple[str,str]] = []
    updates: list[list[str]] = []
    finished_reading_rules = False

    for line in input_lines:
        if "" == line:
            finished_reading_rules = True
        elif not finished_reading_rules:
            parts = line.split('|')
            rules.append((parts[0], parts[1]))
        else:
            updates.append(line.split(','))

    return rules, updates

def _is_correct_update(update: list[str], rules: list[tuple[str,str]]) -> bool:
    page_index = {
        page: index
        for index, page in enumerate(update)
    }

    for rule in rules:
        if rule[0] in page_index and rule[1] in page_index:
            if page_index[rule[0]] > page_index[rule[1]]:
                return False

    return True

class PrintQueue(solution.AbstractSolution):
    def solve_part_one(self, input_lines: list[str]) -> object:
        rules, updates = _parse_input(input_lines)
        middle_page_numbers = [
            int(update[len(update) // 2])
            for update in updates
            if _is_correct_update(update, rules)
        ]

        return sum(middle_page_numbers)
