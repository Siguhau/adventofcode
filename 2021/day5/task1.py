def split_input_into_lists(input_line):
    input_line=input_line.rstrip('\n')
    input=[]
    for elem in input_line.split(" -> "):
        input.append(elem.split(","))

    return input

def check_if_hos_or_ver(input):
    if check_if_hos(input):
        return True
    if check_if_ver(input):
        return True

        
    return False

def check_if_hos(input):
    if input[0][0]==input[1][0]:
        return True
    return False

def check_if_ver(input):
    if input[0][1]==input[1][1]:
        return True
    return False

floor=[[0 for col in range(1000)] for row in range(1000)]
f = open("day5/input.txt", "r")
input = f.readlines()
for line in input:
    splitted = split_input_into_lists(line)
    checked = check_if_hos_or_ver(splitted)
    if checked:
        if check_if_ver(splitted):
            #print(splitted)
            x1=int(splitted[0][0])
            x2=int(splitted[1][0])
            y=int(splitted[0][1])
            for x in range(x1,x2):
                floor[x][y] +=1
                #print(floor[x][y])
        if check_if_hos(splitted):
            y1=int(splitted[0][1])
            y2=int(splitted[1][1])
            x=int(splitted[0][0])
            for y in range(y1,y2):
                floor[x][y] +=1
number=0
for row in floor:
    for elem in row:
        if elem>1:
            number+=1

print(number)