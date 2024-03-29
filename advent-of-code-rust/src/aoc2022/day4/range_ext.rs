use std::ops::Range;

pub trait RangeExt {
    fn contains_range(&self, other: &Range<i32>) -> bool;
    fn overlaps(&self, other: &Range<i32>) -> bool;
}

impl RangeExt for Range<i32> {
    fn contains_range(&self, other: &Range<i32>) -> bool {
        self.start <= other.start && self.end >= other.end
    }

    fn overlaps(&self, other: &Range<i32>) -> bool {
        self.start <= other.end && self.end >= other.start
    }
}
