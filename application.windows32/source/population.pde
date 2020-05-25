class population{
  
int popsize = 5;
individual [] pop = new individual[popsize];
int fittest = 0;

void intitialize(){
    for (int i=0;i<popsize;i++){
    pop[i] = new individual();
    }
}

individual getfittest(){
  int maxfit = -1;
  int maxfitindex = -1;
  for (int i=0;i<popsize;i++){
    if (pop[i].fitness >= maxfit){
     maxfit = pop[i].fitness;
     maxfitindex = i;
    }
  }
  fittest = maxfit;
  return pop[maxfitindex];

}

individual getsecondfittest(){
  int maxfit1=0;
  int maxfit2=0;
  for (int i=0;i<popsize;i++){
    if (pop[i].fitness >= pop[maxfit1].fitness){
      maxfit2=maxfit1;
      maxfit1 = i;
    } else if (pop[i].fitness>pop[maxfit2].fitness){
      maxfit2=i;
    }
  }
  
  return pop[maxfit2];

}

int getleastfit(){
  int leastfit = 10;
  int leastfitindex = popsize+1;
  for (int i=0;i<popsize;i++){
      if (leastfit>=pop[i].fitness){
        leastfit = pop[i].fitness;
        leastfitindex = i;
      }

  
  }
   return leastfitindex;

}


void calculate_fitness(){
for (int i=0;i<popsize;i++){
pop[i].calcfitness();

}
getfittest();


}

}
