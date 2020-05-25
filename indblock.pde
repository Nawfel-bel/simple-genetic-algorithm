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
 
 void show(){
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
 
 
 void setupindivs(individual [] a){
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
