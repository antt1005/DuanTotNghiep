window.ForgetController = function($scope, $http, $location,$rootScope){
    document.getElementById('main-wrapper').style.display = 'none';
    document.getElementById('login').style.display = 'block';
    $scope.form = {};
    $scope.forget = function(){
        $http.put("http://localhost:8080/api/employee/forget",{
            username : $scope.form.username,
            email : $scope.form.email
        }).then(function(resp){
            $http.get("http://localhost:8080/api/employee/getByUsername/"+$scope.form.username).then(function(response){
                // Lấy ngày và giờ hiện tại
var currentDate = new Date();

// Lấy giờ, phút, giây
var hours = currentDate.getHours();
var minutes = currentDate.getMinutes();

// Lấy ngày, tháng, năm
var day = currentDate.getDate();
var month = currentDate.getMonth() + 1; // Tháng bắt đầu từ 0
var year = currentDate.getFullYear();
                var emailData = {
                    to: $scope.form.email,
                    subject: 'Cập nhật mật khẩu lúc ' + hours +' giờ ' + minutes + ' phút ' +day + '/'+month + '/' + year,
                    bodyHtml: '<div style="font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px; max-width: 600px; margin: 0 auto; text-align: center;">' +
                    '<img src="https://i.imgur.com/pjhEmjD.png" alt="Threedomains Logo" style="max-width: 100%; height: auto; margin-bottom: 20px;width:200px;height:100px">' +
                    '<h2 style="color: #333333;">Xin chào, ' + response.data.fullname + '</h2>' +
                    '<p style="color: #666666;">Mật khẩu mới của bạn là: <strong>' + response.data.password + '</strong></p>' +
                    '<p style="color: #666666;">Hãy đảm bảo giữ mật khẩu này an toàn.</p>' +
                    '</div>',
                };
        
                $http.post('http://localhost:8080/api/sendmail', emailData)
                    .then(function (response) {
                        Swal.fire("Cập nhật mật khẩu thành công !","Vui lòng kiểm tra Email của bạn","success");
                        location.href = "#/login"
                    })

            })
        })
        .catch(function (err){
            if (err.status === 400){
                $scope.validationErrors = err.data;
            }
             else{
                Swal.fire("Tài khoản hoặc Email không khớp !","","error");
            }
           
        })
        
    }
}