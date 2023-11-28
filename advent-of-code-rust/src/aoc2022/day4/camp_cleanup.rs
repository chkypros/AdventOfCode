use std::ops::Range;
use crate::aoc2022::day4::range_ext::RangeExt;
use crate::prelude::*;

pub struct CampCleanup;

impl solution::Solution for CampCleanup {

    fn solve_part_one(&self, input_content: &String) -> String {
        input_content.lines()
            .map(parse_sections)
            .filter(|(first, second)| first.contains_range(second) || second.contains_range(first))
            .count()
            .to_string()
    }

    fn solve_part_two(&self, input_content: &String) -> String {
        input_content.lines()
            .map(parse_sections)
            .filter(|(first, second)| first.overlaps(second))
            .count()
            .to_string()
    }
}

fn parse_sections(mapping: &str) -> (Range<i32>, Range<i32>) {
    let Some((first, second)) = mapping.split_once(',')
        else { panic!("Invalid mapping: {mapping:?}") };
    (parse_range(first), parse_range(second))
}

fn parse_range(description: &str) -> Range<i32> {
    let Some((start, end)) = description.split_once('-')
        else { panic!("Invalid range: {description:?}") };
    (start.parse().unwrap())..(end.parse().unwrap())
}