var __extends = (this && this.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
        return extendStatics(d, b);
    }
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
function myFunk(props) {
    return props.firstName + " " + props.lastName;
}
//console.log(myFunk('hello'));
var Person = /** @class */ (function () {
    //constructor, readonly - does not allow arg to be changed
    function Person(name, amountLikesToWork) {
        if (name === void 0) { name = "Jon"; }
        if (amountLikesToWork === void 0) { amountLikesToWork = 0; }
        this.name = name;
        //    name = new String();
        this.firstName = name || 'Jon'; //guarding javascript
        this.workload = amountLikesToWork;
    }
    //method
    Person.prototype.work = function () {
        return this.workload;
    };
    return Person;
}());
var Programmer = /** @class */ (function (_super) {
    __extends(Programmer, _super);
    function Programmer() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    return Programmer;
}(Person));
console.log(new Person());
console.log(new Programmer());
var jon = new Programmer();
jon.firstName = 'Jonathan';
jon.lastName = 'Tran';
jon.secret = 'secret';
console.log(jon);
console.log(myFunk({
    firstName: 'jon',
    lastName: 'tran'
}));
