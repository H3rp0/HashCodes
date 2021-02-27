def common_factor(par1,par2):
	common=0
	lpar1=int(par1[1])
	lpar2=int(par2[1])
	for i in range(2,2+lpar2):
		common +=par1[2:].count(par2[i])
	return min(common, lpar1-common, lpar2-common)


def v_tags (v1,v2,n1,n2):
	vv1=v1.split()
	vv2=v2.split()
	lvv1=2+int(vv1[1])
	lvv2=2+int(vv2[1])
	out=[[n1,n2]]
	for i in range(2,lvv1):
		for j in range(2,lvv2):
			if vv1[i] == vv2[j]:
				vv2.remove(vv2[j])
				vv2.insert(j,"PUTOALEX")
	for i in range(0,vv2.count("PUTOALEX")):
		vv2.remove("PUTOALEX")
	
	out.extend(vv1[2:])
	out.extend(vv2[2:])
	out.insert(1,len(vv1[2:])+len(vv2[2:]))
	return out
				
from io import open

filename = input()
f = open(filename)
long = f.readlines()
f.close()
total = 0
vbuff=0
ihaveone=False
slidelist=[]
cont1=0
for i in range(1,int(long[0])+1):
	temp = long[i][0]
	if temp == "H":
		total +=1
		metemos = long[i].split()
		metemos[0]=[i-1]
		slidelist.append(metemos)
	else:
		total +=0.5
		if ihaveone == False:
			vbuff = i
			ihaveone=True
		else:
			slidelist.append(v_tags(long[vbuff],long[i],vbuff-1,i-1))
			ihaveone=False

salida = open("out.txt","w")
salida.write(str(len(slidelist)))
salida.write("\n")

for i in range(0,len(slidelist)-1):
	print("Vamos por el ", i)
	max=0
	maxpos=i+1
	for j in range(i+1,len(slidelist)):
		temp=common_factor(slidelist[i],slidelist[j])
		if temp > max:
			maxpos=j
			max=temp
	for h in slidelist[i][0]:
		salida.write(str(h))
		salida.write(" ")
	salida.write("\n")
	cambio=slidelist[i+1]
	slidelist[i+1]=slidelist[maxpos]
	slidelist[maxpos]=cambio

for h in slidelist[len(slidelist)-1][0]:
		salida.write(str(h))
		salida.write(" ")
salida.write("\n")
salida.close()