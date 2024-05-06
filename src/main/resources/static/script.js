function validatePhoneNumber(){
    const phoneNumber = $("#phoneNumber").val();
    const regexp = /^[0-9]{8}$/;
    const ok = regexp.test(phoneNumber);

    if (!ok){
        $("#wrongPhoneNumber").html("Phone number should be 8 digits long");
        return false;
    } else {
        $("#wrongPhoneNumber").html("");
        return true;
    }
}
function validateEmail(){
    const email = $("#email").val();
    const regexp = /^[A-Za-z0-9._+/-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2}$/;
    const ok = regexp.test(email);

    if (!ok){
        $("#wrongEmail").html("You have to enter a valid email");
        return false;
    } else {
        $("#wrongEmail").html("");
        return true;
    }
}

function sendForm(){
    const citizen = {
        firstname: $("#firstname").val(),
        surname: $("#surname").val(),
        DoB: $("#dateOfBirth").val(),
        SSN: $("#SSN").val(),
        phoneNumber: $("#phoneNumber").val(),
        email: $("#email").val(),
        city: $("#city").val(),
        street: $("#street").val()
    };

    console.log(citizen)

}
$.get("/hello", function (data){console.log(data)})
