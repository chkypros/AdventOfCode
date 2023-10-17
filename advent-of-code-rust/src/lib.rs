mod common {
    #[cfg(test)]
    pub mod utils;

    pub mod solution;

    #[cfg(test)]
    pub mod solution_test;
}

mod aoc2022 {
    mod day1 {
        mod calorie_counting;
        mod calorie_counting_test;
    }
    mod day2 {
        mod rock_paper_scissors;
        mod rock_paper_scissors_test;
    }
}

mod prelude {
    pub use crate::common::*;
    pub use crate::common::utils::*;
    pub use crate::common::solution::*;
    pub use crate::common::solution_test::*;
}
