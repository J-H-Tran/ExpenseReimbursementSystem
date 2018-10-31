function myFunk(props) : string{
  return `${props.firstName} ${props.lastName}`;
}

//console.log(myFunk('hello'));

class Person {
  //properties
  firstName: string; //default name returned, unless arg is passed in
  workload: number;
  //constructor, readonly - does not allow arg to be changed
  constructor(readonly name: string = "Jon", amountLikesToWork: number = 0) {
//    name = new String();
    this.firstName = name || 'Jon'; //guarding javascript
    this.workload = amountLikesToWork;
  }
  //method
  work() {
    return this.workload;
  }
}

class Programmer extends Person implements Coder{
  skill: number;
  lastName: string;
  private secret: string;

}
// if someone imports without qualifier give the default
export interface Coder {
  firstName: string;
  lastName: string;
  skill?: number; //optionally

}

console.log(new Person());
console.log(new Programmer());

const jon = new Programmer();
jon.firstName = 'Jonathan';
jon.lastName = 'Tran';

console.log(jon);

console.log(myFunk({
  firstName: 'jon',
  lastName: 'tran'
}));

export default {
  coder: jon,
}
