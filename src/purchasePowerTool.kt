/*
William C.
Last update: 1/24/24
 */
import java.util.Scanner




fun main(){
    //This will get purchase price
    val pp = buy().purchasePrice()
    //This will get the term
    val term = buy().termGetter()
    //This will calculate how many payments based on purchase price and term
    val pay = buy().paymentCalculator(pp, term)
    //This will get user income info
    val income = income()
    //This will plug information needed to generate a purchase power ranking
    alg(pp, pay, term, income, debt(income))


}

//////////////////////////////////////////////////user input function
//THis will prompt for user input and return a string
fun userInput(): String{
    // Create a Scanner object for reading input
    val scanner = Scanner(System.`in`)

    // Prompt user for input
    print("Enter a Response ")

    // Read user input using Scanner
    val userInput = scanner.nextLine()
    return userInput
}

////////////////////////////////////////////////////Input checker (int or float)
//This will take in a string, determine if it is an integer or float and return a float.
//If given string isn't a valid entry it will loop this function until a string that can be converted to a float is entered.

fun intOrFloatChecker(input: String): Float {
    var num = input
    if (num.toIntOrNull() != null) {
        //println("the number is an integer but it will be converted to a float")
        var newNum = String.format("%.2f", num.toFloat())
        return newNum.toFloat()
    } else if (num.toFloatOrNull() != null) {
        //println("it isn't an integer but is a float")
        var newNum = String.format("%.2f", num.toFloat())
        return newNum.toFloat()
    } else {
        println("'$num' is not a valid entry")
        println("Please enter a valid entry.  Example of Valid entry: 24.00, 45.01 or 21500.99")
        var x = intOrFloatChecker(userInput()) // this should return a prompt to ask for user input again.  Think loop
        return x
    }


}
////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////Buy Section (Class)
/*
    class buy{
        purchasePrice()
        termGetter()
        paymentCalculator()

    }

 */
class buy{
    //Gets purchase price from user
    fun purchasePrice(): Float{
        println("How much is the purchase price?")
        var pp = intOrFloatChecker(userInput())
        println()
        return pp
    }
    //Gets term from user
    fun termGetter(): Int{
        println("How many months will be given to payoff the purchase?")
        var term = intOrFloatChecker(userInput()).toInt()
        println()
        return term
    }
    //Caluclates the price of the purchase
    fun paymentCalculator(purchasePrice: Float, term: Int): Float{
        var pay = purchasePrice / term.toFloat()
        pay = String.format("%.2f", pay).toFloat()
        println("With a purchase price of ...$purchasePrice and a term of...$term... the monthly payments will be $pay ")
        println()
        return pay.toFloat()
    }

}


////////////////////////////////////////////////////Income Section
/*
    Variables
        paytype
        hourly
        rate
        hours
        weeksInYear
        monthsInYear
        salary
        xtraIncome
        addIncome
        totalIncome


 */
fun income(): Float {
    println()
    println("-------------------Start Income Section-----------------------------")
    println()
    println("Do you get paid hourly or by salary? h = hourly s = salary")
    var payType = userInput()
    val monthsInYear: Int = 12
    //If paytype is hourly then monthly income will be determined by hourly rate multiplied by hours worked in a week, multiplied by weeks in a year, divided months in a year.
    //((Rate x hours x 52) /12)
    if (payType == "h" || payType == "H" || payType == "hourly" || payType == "Hourly") {
        val weeksInYear: Int = 52
        println("How much do you get paid hourly?")
        println()
        var rate = intOrFloatChecker(userInput())
        println("How many hours a week do you work?")
        println()
        var hours = intOrFloatChecker(userInput()).toInt()
        var hourly = (((rate * hours) * weeksInYear) / monthsInYear)
        println("You get paid, $rate an hour, working $hours a week, which gives you a gross monthlyl income of $hourly")
        println()
        println("How much additional income do you get paid on a monthly basis?")
        println()
        var xtraIncome: Float = intOrFloatChecker(userInput())
        var totalIncome = String.format("%.2f", (hourly + xtraIncome)).toFloat()
        println("Your total monthly income is... $totalIncome")///////////
        println()

        return totalIncome

    }else if (payType == "s" || payType == "S" || payType == "salary" || payType == "Salary"){
        //If payType is salary then monthly income will be annual salary divided by months of the year
        //annual salary / 12
        println("What is your annual income?")
        println()
        var salary = intOrFloatChecker(userInput())
        var monthly = salary / monthsInYear
        println("With an annual salary of $salary your income is $monthly")////////////////////////check here for number format
        println()
        println("How much additional income do you get paid on a monthly basis?")
        println()
        var xtraIncome: Float = intOrFloatChecker(userInput())
        var totalIncome = monthly + xtraIncome
        println("Your total monthly income is... $totalIncome")
        println()
        return totalIncome
        }
    else{
        println("'$payType' was an invalid entry")
        println("Valid Entries for Hourly: h or h or hourly or Hourly")
        println("Valid Entries for Salary: s or S or salary or Salary")
        println("Please use a valid entry.  ")
        println()
        return income()


    }

}
///////////////////////////////////////////////////End Of Income Section


//////////////////////////////////////////////////Debt Loader Class
//This class holds the different debt loads for different types of expenses.
class debtLoader{

    //Car and Food debt load is 15% of income
    fun carAndFoodDebtload(income: Float, answer: Float, check: String): Float {

        if (income * .15 >  answer) {
            if(check == "car"){
                println("The amount you put in for your car payment is less than 15% of income.")
                println("Min Debt Load: ${income *.15} > Your car payment: ${answer} (${answer / income}% of your income)")
                println("Car payment will be debt loaded to 15% in case of more expensive car payment in the future.")
                println()
            } else{
                println("The amount you put in for food is less than 15% of income.")
                println("Min Debt Load: ${income *.15} > Food expenses: ${answer} (${answer / income}% of your income) ")
                println("The average amount going towards food is 15% of income and the debt load for food will represent that.")
                println()
            }
            println("The amount you will be debt loaded for $check will be...${income*.15} ")
            println()
            var debtload = income * .15
            return debtload.toFloat()
        } else {
            println("You debt load for $check is...${answer} ")
            println()
            return (answer)


        }
    }
    //Rent or mortgage debt load is about 28% of income or around 1300.  The 750 is saying at least they are spliting a mortgage or rent with someone.
    fun rentDebtload(answer: Float): Float {

        if (750 > answer) {
            println("The rent or mortgage of ...$answer is less then the minimum debt load of 750.")
            println("Rent or mortgage will be debt loaded to be 750 in anticipation for future rent or mortgage that will be shared.")
            println()
            var debtload = 750
            return debtload.toFloat()
        } else {
            println("The rent or mortgage is...$answer")
            println()
            return answer
        }
    }

}



////////////////////////////////////////////////////Debt section

/*
    Arguments
        Income (income): Float

    Variables
        dl - debtLoader() (class)
        rent : Float
        car :Float
        food :Float
        save :Float
        exp :Float - Returned
 */
fun debt(income: Float ): Float {
    println()
    println("------------------Start of Debt Section-----------------------")
    var dl = debtLoader()

    println("Enter how much your monthly mortgage or rent is.")
    var rent = dl.rentDebtload(intOrFloatChecker(userInput()))

    println("Enter how much is your monthly car payment")
    var car = dl.carAndFoodDebtload(income, intOrFloatChecker(userInput()), "car")

    println("Enter how much you pay for food on a monthly basis.")
    var food = dl.carAndFoodDebtload(income, intOrFloatChecker(userInput()), "food")

    println("As a percentage, how much of your check do you put in savings each month? (Example 10% = .1) .")
    var save = intOrFloatChecker(userInput())
    //This will gurantee that savings percentage is always greater than 0% but no more than 10%
    if (save < 0) {
        println("Invalid entry.  Please enter a percentage grater than 0.")
        println("As a percentage, how much of your check do you put in savings each month? (Example 10% = .1) .")
        println()
        save = intOrFloatChecker(userInput())

    } else  if (save > .1){
        println("You have selected $save % (${save * 100}%) go to savings.  For the purposes of this program, savings will be maxed out at 10%")
        println()
        save = (income *.1).toFloat()
    }
    var exp = (rent + car + food + save)
    println()
    //An output of the variables for user to see
    println("---Expenses overview---")
    println("Rent/Mortgage................$rent")
    println("Car Payment...................$car")
    println("Food Expenses................$food")
    println("Savings......................$save")
    println("Total Monthly Expenses........$exp")
    println()

    return exp

}

////////////////////////////////////////////////////// alg section

/*
Arguments
		(Buy)
			Purchase Price (pp): Float
			Payment (pmt): Float
			Term (term): Float

		(Income)
			Income (income): Float

		(Debt)

			Expenses (exp): Float
Variables

    pp: Float
    pmt: Float
    t: Float
    inc: Float
    exp: Float
    eibp: Float
    sap: Float
    pbt : Float
    verdict : Int
    strong : String --This will be a prescripted message
    moderate: String -- This will be a prescripted message
    weak : String -- This will be a prescripted message
    message: String


 */

//This section will take variables into consideration and put them through various formulas to return a ranking and message.
fun alg(purchasePrice: Float, payment: Float, term: Int, Income: Float, Expenses: Float) {
    println()
    println("-------------------Start of Algorithm section--------------------")


    //Needed variables from the following sections, Buy, Income, Debt
    //Buy
    var pp: Float = purchasePrice
    var pmt: Float = payment
    var t: Int = term
    //Income
    var inc: Float = Income
    //Debt
    var exp: Float = Expenses

    //alg variables
    //Expendable Income Before Payment
    var eibp: Float = inc - exp
    // Savings After Payment
    var sap: Float = eibp - pmt
    //Payback Time
    var pbt: Float
    if (sap < 0) {
        // this would mean that user can't support the payment then it is assumed user is drawing on savings
        println("Income alone will not support payment.  It will be assumed savings will make up the difference")
        var savingsDraw = (eibp - pmt) * -1 // how much will be drawn from savings to complete 1 full payment.
        println("this is savings draw $savingsDraw")
        println()
        pbt = (pp + (savingsDraw * t)) / (eibp)

    } else {
        //This means user doesn't need to draw on savings to pay a complete monthly payment
        pbt = (pp / sap)
        //if payback time is longer than the term...
        if (pbt > t) {
            //check to see if another 12 months with sap + pmt will payoff the pp...
            println("Didn't pass intiial term of... $term")
            println("Checking 12 month extension..")
            if (t + 12 >= t + ((pp - (sap * t)) / (pmt + sap))) {
                // if true then, pbt will change to include the actual that is acceptable...
                println()
                println("Payback time will be completed no later than 12 months after term is completed.")
                pbt = (t + ((pp - (sap * t)) / (pmt + sap)))
                println("The Payback time will be...$pbt")
                println()
            }
            // If false then, pbt will change to show actual pbt...
            else {
                println("Didn't pass initial term of... $term or the the 12 month extension (${term + 12})")
                pbt = (t + ((pp - (sap * t)) / (pmt + sap)))
                println("The time it will take to recuperate the spent money will be...$pbt")
                println()
            }

        }

    }


    //output for user to see
    println("--------Purchase section---------")
    println("Purchase Price................$pp")
    println("Term (months)------------------$t")
    println("Payment (monthly)............$pmt")
    println()
    println("------------Income---------------")
    println("Income (monthly)..............$inc")
    println()
    println("------------Expenses--------------")
    println("Expenses (monthly).............$exp")
    println()
    println("------------Analysis---------------")
    println("Expendable Income Before Payment.....$eibp")
    println("Saving After Payment................$sap")
    println("Payback time (months)...............$pbt")
    println()
    println("------------Verdict---------------")

    var verdict: Int = 0
    //prescripted messages depending on verdict number
    var strong = "Your buying power is ... strong.  Remember this is an estimate but, it would seem that you can support this purchase."
    var moderate = " You buying power is ... moderate.  You aren't the strongest buyer but, with some adjustments you can improve your purchase strength"
    var weak = "Your buying power is... weak.  According to the information you entered, now may not be the best time to make this purchase."

    //var message: String = ""
    println("Payment Section...")
    // if payment can be supported after expenses
    if (inc > (exp + pmt)) {
        verdict += 3
        println("Your income of $inc will support the payment of $pmt after the expenses of $exp")
        println("Income - (payment + expenses) = ${inc - (pmt + exp)}")
        println()
    } else{
        //payment can't be support with income after expenses
        println("Your income of $inc will not support the payment of $pmt")
        println("Your payment + expense being greater than your income (${pmt + exp} > $inc)")
        println()
    }
    println("Debt To Income Ratio Section...")
    //checking the ratio if (expenses + payment) / income <= 68%
    if (((exp +pmt) / inc ) <= .68){
        verdict += 2
        println("The debt to income ratio is acceptable at ${(100 * (exp + pmt)) / inc}%.  Max dti ratio is 68%")
        println()


    }else{
        println("Your debt to income ratio is greater than 68% of your income (${(100 * (exp + pmt)) / inc}%).")
        println("Even if you can 'afford' the payment, you may not be able to support payment long-term")
        println()
    }
    println("Payback Time Section...")
    //Checking if Payback time is less than or equal to the term plus 1 year
    if (pbt <= term +12){
        verdict +=1
        println ("The payback time is acceptable at $pbt months")
        println()
    } else{
        println("The time it will take to recuperate the money spent on purchase will take longer than 1 year after the term")
        println("Payback time > (term + 12), ($pbt months> ${term + 12}) months")
        println()
    }
    println("Purchase Power Ranking")
    println("0-3 = Weak, 4-5 = Moderate, 6 = Strong")
    println("Purchase Power rank is....$verdict")
    println()

    var message = if (verdict >= 5) {
        strong

    } else if (verdict > 3){
        moderate

    } else{
        weak
    }

    println(message)
    println()
    println("----------------------End of Program----------------------")

}