
population Popul ;
individual fittest;
individual second_fittest;
int generationcount=0;

//UI


indblock blocks;
int nmbofblocks = 5;


//this and the method under it checks the number of columns
//float col=0;
//int columnboxes = 0;

//void check(){
  
//  int p=1;
//  while (col<1){

//  col = ((float)height/(20+(float)nmbofblocks*(50+20)/p));
//  p++;

//}  
//columnboxes = floor(col+1);
//}

void setup(){
  size(900,500);
  //check();
  blocks = new indblock(nmbofblocks);
  blocks.show();
noLoop();
}

void makepopulation(population p){
for (int i=0;i<nmbofblocks;i++){
  
     blocks.setupindivs(Popul.pop);

}

}


void draw(){
  float kokx = 0;
  float koky = 800;
  fill(211);
  rect(kokx,koky,10,10);
  
  fill(212);
  rect(600, 100, 300, 80);
  Popul = new population();
  generationcount=0;
Popul.intitialize();

Popul.calculate_fitness();
System.out.println("Generation: " + generationcount + " Fittest: " + Popul.fittest);
while (Popul.fittest <5){
  

generationcount++;
selection();
crossover();
if (random(100)%20<30){
  mutation();
}

Popul.calculate_fitness();
System.out.println("Generation: " + generationcount + " Fittest: " + Popul.fittest);
}

makepopulation(Popul);
String s = "Solution found in generation " + generationcount +"\n and we found a fitness of: "+Popul.getfittest().fitness + "\n with the genes being: "  ;
for (int i = 0; i < Popul.pop[0].genelength; i++) {
           s+=(Popul.getfittest().genes[i]+"  ");
        }
s+= " \nand the second fittest: ";
for (int i = 0; i < Popul.pop[0].genelength; i++) {
           s+=(Popul.getsecondfittest().genes[i]+"  ");
        }
fill(50);
textSize(12);
text(s, 610, 100, 300, 80); 

 System.out.println("\nSolution found in generation " + generationcount);
 System.out.println("Fitness: "+Popul.getfittest().fitness);
System.out.print("Genes: ");
        for (int i = 0; i < Popul.pop[0].genelength; i++) {
            System.out.print(Popul.getfittest().genes[i]+"  ");
        }

        System.out.println("");



}

void selection(){
fittest = Popul.getfittest();
second_fittest = Popul.getsecondfittest();
}

void crossover(){
int crossoverpoint = floor(random(0,Popul.pop[0].genelength));
for (int i=0;i<crossoverpoint;i++){
int temp = fittest.genes[i];
fittest.genes[i] = second_fittest.genes[i];
second_fittest.genes[i] = temp;


}

}

void mutation(){
int mutationoint = floor(random(0,Popul.pop[0].genelength));
if (fittest.genes[mutationoint]==0){
    fittest.genes[mutationoint]=1;
} else {
        fittest.genes[mutationoint]=0;
}

mutationoint = floor(random(0,Popul.pop[0].genelength));
if (second_fittest.genes[mutationoint]==0){
    second_fittest.genes[mutationoint]=0;
}else {
second_fittest.genes[mutationoint]=0;
}


}

individual getfittestoff(){
if (fittest.fitness>second_fittest.fitness){
return fittest;}
else {

return second_fittest;}
}

void addfittestoffsrping(){
fittest.calcfitness();
second_fittest.calcfitness();

int leastfitind = Popul.getleastfit();

Popul.pop[leastfitind] = getfittestoff();



}

void mousePressed(){
redraw();
}
