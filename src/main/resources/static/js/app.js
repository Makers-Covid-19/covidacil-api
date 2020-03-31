
var apiVersion = "v0";
var adress = /api/ + apiVersion;

var app = new Vue({
    el: '#app',
    data: {

        warningText : "",

        phoneNumber : "",
        phoneName : "",

        selectedProvince : {},
        selectedDistrict : {},
        selectedCategory: {},

        categories: [],
        provinces : [],
        provincesDistricts : [],

        succes : ""

    },
    mounted (){
      this.loadProvinces();
      this.loadCategories();
    },
    methods : {
        loadProvinces(){
            var self = this;

            axios.get(adress+ "/provinces")
                .then(function (response) {
                    self.provinces = response.data.data;

                })
                .catch(function (error) {
                    // handle error
                    console.log(error);
                })
                .then(function () {
                    // always executed
                });
        },

        loadDistricts(){
            var self = this;


            axios.get(adress+ "/districts/" + self.selectedProvince.id)
                .then(function (response) {
                    self.provincesDistricts = response.data.data;



                })
                .catch(function (error) {
                    // handle error
                    console.log(error);
                })
                .then(function () {
                    // always executed
                });
        },
        loadCategories(){
            var self = this;

            axios.get(adress+ "/categories")
                .then(function (response) {
                    self.categories = response.data.data;

                })
                .catch(function (error) {
                    // handle error
                    console.log(error);
                })
                .then(function () {
                    // always executed
                });
        },
        send() {
            var self = this;

            // phoneNumber : "",
            //     phoneName : "",
            //
            //     selectedProvince : {},
            // selectedDistrict : {},
            // selectedCategory: {},
            //
            // categories: [],
            //     provinces : [],
            //     provincesDistricts : [],
            var sendingPhone = {
                "name": self.phoneName,
                "phoneNumber": self.phoneNumber,
                "districtId": self.selectedDistrict.id,
                "provinceId": self.selectedProvince.id,
                "neighborhoodId": "",
                "categoryId": self.selectedCategory.id

            }

            var error = false;
            self.warningText = "";

            if(sendingPhone.name == ""){
                error = true;
                self.warningText += "Please set phone name \n";
            }
            if(sendingPhone.phoneNumber.length != 11){
                error = true;
                self.warningText += "Please Enter True Phone number Phone number lenght equal 11 \n";
            }
            if(sendingPhone.provinceId != undefined || sendingPhone.districtId  != undefined){

            }
            else{
                error = true;
                self.warningText += "Please enter Province or Distric \n";
            }
            if(sendingPhone.categoryId == undefined){
                error = true;
                self.warningText += "Please Enter Category \n";
            }

            if(error){

            }
            else{

                axios.post(adress + '/phones', sendingPhone)
                    .then(function (response) {
                        if(response.data.message == "succes")
                        self.succes = "Succesfuly Created";
                        self.phoneName = "";
                        self.phoneNumber = "";
                    })
                    .catch(function (error) {
                        console.log(error);
                    });

            }


        }
    }
})
