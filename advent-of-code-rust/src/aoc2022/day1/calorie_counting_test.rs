#[cfg(test)]
mod tests {
    use rstest::{fixture, rstest};
    use crate::prelude::*;
    use crate::aoc2022::day1::calorie_counting::*;

    const INPUT_FILE: &str = "../inputs/aoc2022/day1/aoc-day1-input.txt";

    #[fixture]
    fn calorie_counting() -> CalorieCounting {
        CalorieCounting {}
    }

    #[fixture]
    fn input_content() -> String {
        read_lines(INPUT_FILE)
    }

    #[rstest]
    fn test_solve(calorie_counting: CalorieCounting) {
        calorie_counting.solve()
    }

    #[rstest]
    fn test_solve_part_one(calorie_counting: CalorieCounting, input_content: String) {
        assert_eq!(calorie_counting.solve_part_one(&input_content), "2250")
    }

    #[rstest]
    fn test_solve_part_two(calorie_counting: CalorieCounting, input_content: String) {
        assert_eq!(calorie_counting.solve_part_two(&input_content), function())
    }
}
