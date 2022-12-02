
def get_most_common_bit_at_place(input_list, place):
    length=len(input_list[0])
    ones=0
    if place >= length:
        return 0
    for line in input_list:
        if line[place]=="1":
            ones +=1
    if ones >=(len(input_list)/2):
        return "1"
    else:
        return "0"

def get_least_common_bit_at_place(input_list, place):
    most = get_most_common_bit_at_place(input_list, place)
    if most =="1":
        return "0"
    else:
        return "1"


f = open("day3/input.txt", "r")
input_unfiltered = f.readlines()

input=[
    "00100",
    "11110",
    "10110",
    "10111",
    "10101",
    "01111",
    "00111",
    "11100",
    "10000",
    "11001",
    "00010",
    "01010"
]
input=[]
for line in input_unfiltered:
    input.append(line.strip())

oxygen_generator_rating_list=[]
co2_scrubbing_rating_list = []

for line in input:
    oxygen_generator_rating_list.append(line)
    co2_scrubbing_rating_list.append(line)
binary_length = len(input[0])

for i in range(binary_length):
    most_common = get_most_common_bit_at_place(oxygen_generator_rating_list,i)
    to_be_removed=[]
    for line in oxygen_generator_rating_list:
        if line[i]!=most_common:
            to_be_removed.append(line)
    for remove_elem in to_be_removed:
        if len(oxygen_generator_rating_list)>1:
            oxygen_generator_rating_list.remove(remove_elem)
    least_common=get_least_common_bit_at_place(co2_scrubbing_rating_list, i)
    to_be_removed=[]
    for line in co2_scrubbing_rating_list:
        if line[i]!=least_common:
            to_be_removed.append(line)
    for remove_elem in to_be_removed:
        if len(co2_scrubbing_rating_list)>1:
            co2_scrubbing_rating_list.remove(remove_elem)

oxygen_generator_rating=oxygen_generator_rating_list[0]
co2_scrubbing_rating=co2_scrubbing_rating_list[0]
oxygen_generator_rating_dec = int(oxygen_generator_rating,2)
co2_scrubbing_rating_dec = int(co2_scrubbing_rating,2)
print(oxygen_generator_rating_dec)
print(co2_scrubbing_rating_dec)
print(oxygen_generator_rating_dec*co2_scrubbing_rating_dec)