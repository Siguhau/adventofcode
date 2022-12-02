"""
    day 1 of Advent of Code 2021, Siguhau
"""


def number_of_deeper_measurements_part1(input):
    last_value = 0
    deeper = -1
    for item in input:
        if item > last_value:
            deeper += 1
        last_value = item
    return deeper


def mod_5(number):
    return number % 5


def number_of_deeper_measurements_part2(input):
    last_items = [0, 0, 0, 0, 0]
    idx = 0
    deeper = 0
    tries = 0
    for item in input:
        tries += 1
        last_items[idx] = item

        if tries > 3:
            sum1 = last_items[mod_5(idx)] + \
                last_items[mod_5(idx-1)] + last_items[mod_5(idx-2)]
            sum2 = last_items[mod_5(idx-1)] + \
                last_items[mod_5(idx-2)] + last_items[mod_5(idx-3)]

            if sum1 > sum2:
                deeper += 1

        idx = mod_5(idx+1)
    return deeper


def main():
    f = open("day1/input.txt", "r")
    input = f.read().splitlines()
    input = list(map(int, input))
    deeper = number_of_deeper_measurements_part1(input)
    print(f"Part 1: Number of deeper measurements is {deeper}")
    deeper2 = number_of_deeper_measurements_part2(input)
    print(f"Part 2: Number of deeper measurements sums is {deeper2}")


main()
