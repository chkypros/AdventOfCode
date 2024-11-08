class CustomMapping:
    def __init__(self, line: str):
        super().__init__()
        line_info = [int(number) for number in line.split()]
        self.destination_start = line_info[0]
        self.source_start = line_info[1]
        self.length = line_info[2]

    def can_map(self, number: int):
        return self.source_start <= number <= self.source_start + self.length

    def map(self, number: int):
        return number - self.source_start + self.destination_start

class Mapper:

    def __init__(self, description: str):
        super().__init__()
        self.description = description[:-5] # Dropping ' map:' at the end
        self.custom_mappings: list[CustomMapping] = []

    def __str__(self):
        return self.description

    def addCustomMapping(self, line: str):
        self.custom_mappings.append(CustomMapping(line))

    def map(self, number: int) -> int:
        for mapping in self.custom_mappings:
            if mapping.can_map(number):
                return mapping.map(number)
        else:
            return number
