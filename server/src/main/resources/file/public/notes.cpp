
#include <iostream>

using namespace std;

const string V1[2]={"CC", "DS"};
const float V2[2]={0.4, 0.6};
const float C1[6]={3.5, 3.5, 2.5, 1.5, 2.5, 3.5};
const float C2[7]={1.5, 1.5, 2.0, 2.5, 2.5, 1.0, 2.0};
const string M1[6]={"Intro Algo/Prog", "Bases de donnees", "Codin/Medias", "Projet BD et Maths ?",
                    "Algorithmes fondamentaux", "Systemes infos"};
const string M2[7]={"Anglais", "Eco-Env", "Algebre", "Maths discretes", "Eco-Org", "PPP", "Techniques d'expression"};

int main()
{
    float note, val, calcul=0, calcul2=0, calcul3=0;

    ///1er module
    cout << "Entrez vos notes du 1er module :" << endl << endl;
    for(int i=0; i<=5; i++)
    {
        note = 0;
        for(int j=0; j<=1; j++)
        {
            cout << "Entrez la note de " << M1[i] << " en " << V1[j] << " : ";
            cin >> val;
          /* if(i==2 || i==3)
            {
                note += val;
                j++;
            }
            else*/
                note += val*V2[j];
        }
        calcul += note*C1[i];
    }
    calcul /= 17;
    cout << endl << endl;

    ///2eme module
    cout << "Entrez vos notes du 2eme module :" << endl << endl;
    for(int i=0; i<=6; i++)
    {
        note = 0;
        for(int j=0; j<=1; j++)
        {
          /*  if(i==0)
                j++;*/
            cout << "Entrez la note de " << M2[i] << " en " << V1[j] << " : ";
            cin >> val;
           /* if(i==0)
                note += val;
            else*/
                note += val*V2[j];
        }
        calcul2 += note*C2[i];
    }
    calcul2 /= 13;
	calcul3 =(calcul + calcul2) /2;
    ///Rsultats
    cout << "\n\nMoyenne au module UE 11 : " << calcul << endl;
    cout << "Moyenne au module UE 12 : " << calcul2 << endl;
	cout << calcul3 << endl;
    return 0;
}
