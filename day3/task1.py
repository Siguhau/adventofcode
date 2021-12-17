
f = open("day3/input.txt", "r")
input_unfiltered = f.readlines()
input=[]
for line in input_unfiltered:
    input.append(line.strip())
gamma=""
epsilon=""
binary_length = len(input[0])
print(binary_length)
for i in range(binary_length):
    ones=0
    for line in input:
        if line[i]=="1":
            ones +=1
            
    if ones > (len(input)/2):
        gamma +="1"
        epsilon +="0"
    else:
        gamma +="0"
        epsilon += "1"
    ones=0
gamma_dec= int(gamma, 2)
epsilon_dec = int(epsilon,2)

print(gamma)
print(epsilon)

print(gamma_dec*epsilon_dec)
    