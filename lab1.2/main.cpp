#include<bits/stdc++.h>
#include<stdlib.h>
#include<string.h>
#include<ctype.h>

using namespace std;

int isKeyword(char buffer[]){
    char keywords[32][10] =
    {"fnc","char","double","float","int","long","return","main"};
    int i, flag = 0;
    for(i = 0; i < 32; ++i){
        if(strcmp(keywords[i], buffer) == 0){
            flag = 1;
            break;
        }
    }
    return flag;
}

int main(){
    char ch, buffer[15],b[30],math_op[]="+-*/=",numer[]=".0123456789",separator[]=",;\(){}[]'':";
    ifstream fin("language.txt");
    int mark[1000]={0};
    int i,j=0,kc=0,ic=0,lc=0,mc=0,nc=0,oc=0,aaa=0;
    vector < string > keyw;
    vector<char >identif;
    vector<char>opera;
    vector<string>number;
     vector<char>separat;

    while(!fin.eof()){
           ch = fin.get();
          for(i = 0; i < 12; ++i){
               if(ch == separator[i]){
                    int aa=ch;
                if(mark[aa]!=1){
                    separat.push_back(ch);
                    mark[aa]=1;
                    ++oc;
                }
               }
        }

        for(i = 0; i < 5; ++i){
               if(ch == math_op[i]){
                    int aa=ch;
                if(mark[aa]!=1){
                    opera.push_back(ch);
                    mark[aa]=1;
                    ++mc;
                }
               }
        }
        if(ch=='0' || ch=='1' || ch=='2' || ch=='3' || ch=='4' || ch=='5' || ch=='6' || ch=='7' || ch=='8' || ch=='9' || ch=='.' ||ch == ' ' || ch == '\n' || ch == ';'){

            if(ch=='0' || ch=='1' || ch=='2' || ch=='3' || ch=='4' || ch=='5' || ch=='6' || ch=='7' || ch=='8' || ch=='9' || ch=='.')b[aaa++]=ch;
            if((ch == ' ' || ch == '\n' || ch == ';') && (aaa != 0)){
                   b[aaa] = '\0';
                   aaa = 0;
                   char arr[30];
                    strcpy(arr,b);
                        number.push_back(arr);
                    ++nc;

                }
        }


           if(isalnum(ch)){
               buffer[j++] = ch;
           }
           else if((ch == ' ' || ch == '\n') && (j != 0)){
                   buffer[j] = '\0';
                   j = 0;

                   if(isKeyword(buffer) == 1){

                       keyw.push_back(buffer);
                       ++kc;
                   }
                   else{



                    if(buffer[0]>=97 && buffer[0]<=122) {
                        if(mark[buffer[0]-'a']!=1){
                        identif.push_back(buffer[0]);
                        ++ic;
                        mark[buffer[0]-'a']=1;
                       }

                    }

                   }
           }
    }

    fin.close();
    printf("Keywords: ");
     for(int i=0;i<kc;++i){
            if(i==kc-1){
                cout<<keyw[i]<<"\n";
            }
            else {
                cout<<keyw[i]<<" ";
            }
    }
    printf("Identifiers: ");
     for(int i=0;i<ic;++i){
        if(i==ic-1){
                cout<<identif[i]<<"\n";
            }
            else {
                cout<<identif[i]<<" ";
            }
    }
    printf("Operators: ");
    for(int i=0;i<mc;++i){
            if(i==mc-1){
                cout<<opera[i]<<"\n";
            }
            else {
                cout<<opera[i]<<" ";
            }
    }
    printf("Numbers: ");
    for(int i=0;i<nc;++i){
            if(i==nc-1){
                cout<<number[i]<<"\n";
            }
            else {
                cout<<number[i]<<" ";
            }

    }
    printf("Separators: ");
    for(int i=0;i<oc;++i){
            if(i==oc-1){
                cout<<separat[i]<<"\n";
            }
            else {
                cout<<separat[i]<<" ";
            }

    }

    return 0;
}
