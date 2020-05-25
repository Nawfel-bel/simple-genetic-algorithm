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
  
  void calcfitness(){
  fitness =0;
  for (int i=0;i<genelength;i++){
    if (genes[i]==1){
      fitness ++;
     }
    }
  }


}
