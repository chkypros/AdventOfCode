#[cfg(test)]
mod tests {
    use rstest::{fixture, rstest};
    use crate::prelude::*;
    use super::super::rock_paper_scissors::*;

    const PART_ONE_ANSWER: &'static str = "15";
    const PART_TWO_ANSWER: &'static str = "12";

    #[fixture]
    fn implementation() -> impl solution::Solution {
        RockPaperScissors {}
    }

    #[fixture]
    fn sample_input_content() -> String {
        utils::get_labelled_input(file!(), "-sample")
    }

    #[rstest]
    fn test_solve(implementation: impl solution::Solution) {
        solution_test::solve_all(file!(), implementation);
    }

    #[rstest]
    fn test_solve_part_one(implementation: impl solution::Solution, sample_input_content: String) {
        assert_eq!(implementation.solve_part_one(&sample_input_content), PART_ONE_ANSWER)
    }

    #[rstest]
    fn test_solve_part_two(implementation: impl solution::Solution, sample_input_content: String) {
        assert_eq!(implementation.solve_part_two(&sample_input_content), PART_TWO_ANSWER)
    }

    //region Part One
    #[rstest]
    fn test_solve_single_round_win(implementation: impl solution::Solution) {
        let input = "A Y".to_string();
        assert_eq!(implementation.solve_part_one(&input), "8")
    }

    #[rstest]
    fn test_solve_single_round_draw_rock(implementation: impl solution::Solution) {
        let input = "A X".to_string();
        assert_eq!(implementation.solve_part_one(&input), "4")
    }

    #[rstest]
    fn test_solve_single_round_lose_paper(implementation: impl solution::Solution) {
        let input = "C Y".to_string();
        assert_eq!(implementation.solve_part_one(&input), "2")
    }

    #[rstest]
    fn test_solve_multiple_round_win_lose(implementation: impl solution::Solution) {
        let input = "A Y\nC Y\n".to_string();
        assert_eq!(implementation.solve_part_one(&input), "10")
    }
    //endregion

    //region Part Two
    #[rstest]
    fn test_solve_single_round_draw_part2(implementation: impl solution::Solution) {
        let input = "A Y".to_string();
        assert_eq!(implementation.solve_part_two(&input), "4")
    }

    #[rstest]
    fn test_solve_single_round_lose_part2(implementation: impl solution::Solution) {
        let input = "A X".to_string();
        assert_eq!(implementation.solve_part_two(&input), "3")
    }

    #[rstest]
    fn test_solve_single_round_win_part2(implementation: impl solution::Solution) {
        let input = "C Z".to_string();
        assert_eq!(implementation.solve_part_two(&input), "7")
    }

    #[rstest]
    fn test_solve_multiple_round_win_lose_part2(implementation: impl solution::Solution) {
        let input = "A Y\nC Z\n".to_string();
        assert_eq!(implementation.solve_part_two(&input), "11")
    }
    //endregion
}
