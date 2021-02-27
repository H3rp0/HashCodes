def comparasion(par1,par2):
	common=0
	ppar1=par1.split()
	ppar2=par2.split()
	lpar1=int(ppar1[1])
	lpar2=int(ppar2[1])
	for i in range(2,2+lpar2):
		common +=par1[2:].count(par2[i])
	return common