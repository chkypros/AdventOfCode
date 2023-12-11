#[cfg(test)]
mod tests {
    use rstest::{fixture, rstest};
    use crate::prelude::*;
    use super::super::if_you_give_a_seed_a_fertilizer::*;

    const PART_ONE_ANSWER: &'static str = "35";
    const PART_TWO_ANSWER: &'static str = "answer2";

    #[fixture]
    fn implementation() -> impl solution::Solution {
        IfYouGiveASeedAFertilizer {}
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
    fn test_p1_single_entry_maps(implementation: impl solution::Solution) {
        let input = "seeds: 79

seed-to-soil map:
79 79 1

soil-to-fertilizer map:
79 79 1

fertilizer-to-water map:
79 79 1

water-to-light map:
79 79 1

light-to-temperature map:
79 79 1

temperature-to-humidity map:
79 79 1

humidity-to-location map:
79 79 1".to_string();
        assert_eq!(implementation.solve_part_one(&input), "79")
    }
}
