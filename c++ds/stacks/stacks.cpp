#include <iostream>
#include <vector>
#include <string>
using namespace std;



class stacker{
    public:
        int stack[10];
        int top = -1;
        int capacity = 0;
    stacker(int capacity=0){
        this->capacity = capacity;
    }
    void push(int element){
        if(!isfull()){
            top++;
            stack[top] = element;
        }
    }
    bool isempty(){
        if(top == -1){
            return true;
        }
        return false;
    }
    int toper(){
        if(top != -1){
            return stack[top];
        }
        return -1;
    }
    int pop(){
        if(!isempty()){
            top--;
            return stack[top+1];
        }   
        return -1;
    }
    bool isfull(){
        if(top+1 == capacity){
            return true;
        }
        return false;
    }
    void display(){
        cout <<"\n";
        for(int i = 0; i <= top; i++){
            cout << stack[i] <<" ";
        }
        cout <<endl;
    }
};


int main(){

    stacker st(5);
    cout << "is empty : " << st.isempty();
    st.push(20);
    st.display();
    st.push(30);
    st.push(40);
    st.push(50);
    st.display();
    cout << "\npoped : " << st.pop();
    st.push(60);
    cout << "\nis empty: "<<st.isempty();
    st.push(100);
    cout << "\nis full : "<<st.isfull();
    st.display();

    return 0;
}