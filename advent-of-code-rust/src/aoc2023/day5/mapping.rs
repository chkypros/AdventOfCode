#[derive(Debug)]
pub struct Mapping {
    source_start: u64,
    destination_start: u64,
    length: u64,
}

impl Mapping {
    pub fn correlate(&self, source: u64) -> Option<u64> {
        if source < self.source_start || source >= self.source_start + self.length {
            return None
        }

        Some(source - self.source_start + self.destination_start)
    }
}

pub fn from_line(line: &str) -> Mapping {
    let numbers: Vec<u64> = line
        .split(" ")
        .map(&str::parse)
        .map(Result::unwrap)
        .collect();

    Mapping {
        source_start: numbers[1],
        destination_start: numbers[0],
        length: numbers[2],
    }
}
