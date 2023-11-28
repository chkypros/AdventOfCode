#[cfg(test)]
mod tests {
    use rstest::{fixture, rstest};
    use crate::prelude::*;
    use super::super::camp_cleanup::*;

    const PART_ONE_ANSWER: &'static str = "2";
    const PART_TWO_ANSWER: &'static str = "4";

    #[fixture]
    fn implementation() -> impl solution::Solution {
        CampCleanup {}
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

    #[rstest]
    fn test_p1_no_overlap(implementation: impl solution::Solution) {
        let input = "1-2,3-4".to_string();
        assert_eq!(implementation.solve_part_one(&input), "0")
    }

    #[rstest]
    fn test_p2_some_overlap(implementation: impl solution::Solution) {
        let input = "1-3,3-4".to_string();
        assert_eq!(implementation.solve_part_two(&input), "1")
    }
}
