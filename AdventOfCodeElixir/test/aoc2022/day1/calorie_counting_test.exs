defmodule CalorieCountingTest do
  use ExUnit.Case

  @test_configuration [
    file_path: __ENV__.file,
    module_under_test: CalorieCounting,
    expected_answer: [
      part_one: 71300,
      part_two: 209691,
      part_one_sample: 24000,
      part_two_sample: 45000
   ]
  ]

  test "solve part one", do: AdventOfCodeUtils.run_test(@test_configuration, :part_one)
  test "solve part two", do: AdventOfCodeUtils.run_test(@test_configuration, :part_two)
  test "solve part one sample", do: AdventOfCodeUtils.run_test(@test_configuration, :part_one_sample)
  test "solve part two sample", do: AdventOfCodeUtils.run_test(@test_configuration, :part_two_sample)

  test "solve for a single item", do:
    assert @test_configuration[:module_under_test].solve_part_one(["1000"]) == 1000

  test "solve for multiple elfs with single item", do:
    assert @test_configuration[:module_under_test].solve_part_one(["1000", "", "2000"]) == 2000
end
