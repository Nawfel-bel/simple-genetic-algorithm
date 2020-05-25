import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class firstgenetic extends PApplet {


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

public void setup(){
  
  //check();
  blocks = new indblock(nmbofblocks);
  blocks.show();
noLoop();
}

public void makepopulation(population p){
for (int i=0;i<nmbofblocks;i++){
  
     blocks.setupindivs(Popul.pop);

}

}


public void draw(){
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

public void selection(){
fittest = Popul.getfittest();
second_fittest = Popul.getsecondfittest();
}

public void crossover(){
int crossoverpoint = floor(random(0,Popul.pop[0].genelength));
for (int i=0;i<crossoverpoint;i++){
int temp = fittest.genes[i];
fittest.genes[i] = second_fittest.genes[i];
second_fittest.genes[i] = temp;


}

}

public void mutation(){
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

public individual getfittestoff(){
if (fittest.fitness>second_fittest.fitness){
return fittest;}
else {

return second_fittest;}
}

public void addfittestoffsrping(){
fittest.calcfitness();
second_fittest.calcfitness();

int leastfitind = Popul.getleastfit();

Popul.pop[leastfitind] = getfittestoff();



}

public void mousePressed(){
redraw();
}
class poin{
float x;
float y;
public poin(float x1,float y1 ){
x = x1;
y = y1;
}
}


class indblock{
  float horispace =400;
  float vertispace = 20;
  int nmb=0;
  String text = "WASSABI";
  poin [] points;
  int column_boxes=7 ;
  
  
  public indblock(int x){
  nmb =x;
   points =  new poin[x];
  }
 
 public void show(){
   int boxes = nmb;
   int k=0;
 float startver = 20;
 float starthor = 100;
 for (int i=0;i<floor((nmb/column_boxes)+1);i++){
   for (int j=0;j<column_boxes;j++){
     if (boxes==0){
     break;}
     

    
     fill(129, 206, 15);
     rect(starthor,startver,300,50);
     points [k] = new poin(starthor,startver);
     k++;
     startver+=vertispace+50;
     boxes--;
     
    }
    startver = vertispace;
    starthor +=horispace;
 }
 
 }
 
 
 public void setupindivs(individual [] a){
 for (int i=0;i<nmb;i++){
   float x = (points[i].x)+5;
   float y = (points[i].y)+5;
   
     for(int l=0;l<5;l++){
       if (a[i].genes[l]==1){
       fill(255);}
       else {
       fill(23);}
       rect (x,y,40,40);

       x+=60;
     }
   
 }
 
 
 }


}
class individual{
int fitness;
int genelength =5;
int genes[] = new int[genelength];


  public individual(){
  for (int i=0;i<genelength;i++){
    genes[i] = floor(random(0,2));
  
  }
  
  fitness =0;
  
  }
  
  public void calcfitness(){
  fitness =0;
  for (int i=0;i<genelength;i++){
    if (genes[i]==1){
      fitness ++;
     }
    }
  }


}
class population{
  
int popsize = 5;
individual [] pop = new individual[popsize];
int fittest = 0;

public void intitialize(){
    for (int i=0;i<popsize;i++){
    pop[i] = new individual();
    }
}

public individual getfittest(){
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

public individual getsecondfittest(){
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

public int getleastfit(){
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


public void calculate_fitness(){
for (int i=0;i<popsize;i++){
pop[i].calcfitness();

}
getfittest();


}

}
  public void settings() {  size(900,500); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--stop-color=#cccccc", "firstgenetic" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
