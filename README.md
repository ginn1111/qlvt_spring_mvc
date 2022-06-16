- Tài khoản MS SQL Server usernam = sa, password = 123
- Thêm thư viện sau vào project (link download: https://drive.google.com/file/d/1yfCry2C02LjoVxOtC0p66KSF9kKwvWQt/view?usp=sharing)
#Luồng dữ liệu xử lý như sau:
	- Controller gọi các lớp service để lấy dữ liệu cần thiết.
	- Để xử lý dữ liệu và trả về cho controller các lớp service sẽ gọi cho các lớp DAO.
	- Các lớp DAO được gọi sẽ truy vấn lên db (dùng hibernate nên ta thao tác với entity, trước khi thao tác ta cần ánh xạ các table trong db thành các entity, mở package entity để xem chi tiết).
	- Sau khi lấy được db cần thiết, các lớp DAO trả về db cho service.
	- Service xử lý logic trả về model (chứa các thuộc tính cần thiết để render lên UI) cho controller.
	- Sau cùng controller sẽ trả model cho view và render lên.
	
	Bao gồm các package: dao, service, model, entity
	
#Cụ thể xem trong controller: QuanlyController, requestmapping("nhan-vien") để hiểu thêm.
