#[cfg(test)]
mod tests {
    use rstest::{fixture, rstest};
    use crate::prelude::*;
    use super::super::calorie_counting::*;

    const PART_ONE_ANSWER: &'static str = "24000";
    const PART_TWO_ANSWER: &'static str = "45000";

    #[fixture]
    fn implementation() -> impl solution::Solution {
        CalorieCounting {}
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
    fn test_one_elf_one_food(implementation: impl solution::Solution) {
        let input = "1000".to_string();
        assert_eq!(implementation.solve_part_one(&input), "1000")
    }

    #[rstest]
    fn test_one_elf_many_foods(implementation: impl solution::Solution) {
        let input = "1000\n2000".to_string();
        assert_eq!(implementation.solve_part_one(&input), "3000")
    }

    #[rstest]
    fn test_many_elfs_one_food_each(implementation: impl solution::Solution) {
        let input = "1000\n\n2000\n\n3000".to_string();
        assert_eq!(implementation.solve_part_one(&input), "3000")
    }

    #[rstest]
    fn test_many_elfs_many_foods_each(implementation: impl solution::Solution) {
        let input = "1000\n1000\n\n10000\n\n2000\n3000".to_string();
        assert_eq!(implementation.solve_part_one(&input), "10000")
    }
}
