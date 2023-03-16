
    
INSERT INTO account ( id, email, account_password) values (account_seq.NEXTVAL, 'darklord0305@gmail.com', '123456789');
INSERT INTO account ( id, email, account_password) values (account_seq.NEXTVAL, 'thelionking0305@gmail.com', '123456789');
INSERT INTO account ( id, email, account_password) values (account_seq.NEXTVAL, 'thanhluan130201@gmail.com', '123456789');
INSERT INTO account ( id, email, account_password) values (account_seq.NEXTVAL, '0.199qui@gmail.com', '123456789');
INSERT INTO account ( id, email, account_password) values (account_seq.NEXTVAL, 'nguyenvanan@gmail.com', '123456789');
INSERT INTO account ( id, email, account_password) values (account_seq.NEXTVAL, 'nguyenthiphuong123@gmail.com', '123456789');
INSERT INTO account ( id, email, account_password) values (account_seq.NEXTVAL, 'dinhvanbua125@gmail.com', '123456789');
INSERT INTO account ( id, email, account_password) values (account_seq.NEXTVAL, 'helloworld125@gmail.com', '123456789');
INSERT INTO account ( id, email, account_password) values (account_seq.NEXTVAL, 'nguyenvanhieu123@gmail.com', '123456789');
INSERT INTO account ( id, email, account_password) values (account_seq.NEXTVAL, 'buivantinh123@gmail.com', '123456789');

--TRIGGER CREATE CART WHEN INSERT NEW USER
/


CREATE OR REPLACE TRIGGER CREATE_CART
AFTER INSERT
    ON USER_WEBSITE
    FOR EACH ROW
BEGIN

    INSERT INTO CART(ID, TOTAL, USER_ID) VALUES (:NEW.ID, 0, :NEW.ID);

END;
/
--TABLE USER_WEBSITE
--DROP TRIGGER

    
INSERT INTO user_website (id, user_name, address, phone, id_account) values (user_website_seq.NEXTVAL, 'Nguyễn Viết Quý', 'Chư Puh, Gia Lai', '0134567896', 1);
INSERT INTO user_website (id, user_name, address, phone, id_account) values (user_website_seq.NEXTVAL, 'Phạm Phúc Hậu', 'Vũng Tàu', '0125487898', 2);
INSERT INTO user_website (id, user_name, address, phone, id_account) values (user_website_seq.NEXTVAL, 'Lê Thành Luân', 'Vũng Tàu', '0125487897', 3);
INSERT INTO user_website (id, user_name, address, phone, id_account) values (user_website_seq.NEXTVAL, 'Nguyễn Văn A', 'TP.Hồ Chí Minh', '0125487154', 4);
INSERT INTO user_website (id, user_name, address, phone, id_account) values (user_website_seq.NEXTVAL, 'Đinh Công Lương', 'Quảng Bình', '0125487458', 5);
INSERT INTO user_website (id, user_name, address, phone, id_account) values (user_website_seq.NEXTVAL, 'Duy Mạnh', 'Nghệ An', '0125487487', 6);
INSERT INTO user_website (id, user_name, address, phone, id_account) values (user_website_seq.NEXTVAL, 'Nguyễn Văn Minh', 'Bình Thuận', '0125487879', 7);
INSERT INTO user_website (id, user_name, address, phone, id_account) values (user_website_seq.NEXTVAL, 'Nguyễn Văn Sú', 'Lào Cai', '0125487147', 8);
INSERT INTO user_website (id, user_name, address, phone, id_account) values (user_website_seq.NEXTVAL, 'Aladin', 'Bình Dương', '0125484587', 9);
INSERT INTO user_website (id, user_name, address, phone, id_account) values (user_website_seq.NEXTVAL, 'Nguyễnn Văn Hiếu', 'Dak Lak', '0125487852', 10);


    
INSERT INTO coupon VALUES (coupon_seq.NEXTVAL, 'Khuyến mãi cho khách hàng mới', 0.1, 0, 50000, 'KMKHM', 50, 0);
INSERT INTO coupon VALUES (coupon_seq.NEXTVAL, 'Giảm 10% Giảm tối đa 50,000đ đơn tối thiểu 99,000đ', 0.1, 99000, 50000, 'HUJUI', 30, 0);
INSERT INTO coupon VALUES (coupon_seq.NEXTVAL, 'Giảm 20,000đ đơn tối thiểu 199,000đ', 0, 199000, 20000, 'HUYEJ', 30, 0);
INSERT INTO coupon VALUES (coupon_seq.NEXTVAL, 'Giảm 10% Giảm tối đa 300,000đ đơn tối thiểu 499,000đ', 0.1, 499000, 300000, 'YUITLR', 30, 0);
INSERT INTO coupon VALUES (coupon_seq.NEXTVAL, 'Giảm 50,000đ đơn tối thiểu 0đ', 0, 0, 50000, 'QIEHFU', 30, 0);
INSERT INTO coupon VALUES (coupon_seq.NEXTVAL, 'Giảm 10,000đ đơn tối thiểu 120,000đ', 0, 120000, 10000, 'TEWUYG', 30, 0);
INSERT INTO coupon VALUES (coupon_seq.NEXTVAL, 'Giảm 5% Giảm tối đa 500,000đ đơn tối thiếu 300,000đ', 0.05, 300000, 500000, 'OIUYTR', 30, 0);
INSERT INTO coupon VALUES (coupon_seq.NEXTVAL, 'Giảm 150,000đ đơn tối thiểu 1,500,000đ', 0, 1500000, 150000, 'RTEGDT', 30, 0);
INSERT INTO coupon VALUES (coupon_seq.NEXTVAL, 'Giảm 10% Giảm tối đa 60,000đ đơn tối thiểu 400,000đ', 0.1, 400000, 60000, 'YEUWGY', 30, 0);
INSERT INTO coupon VALUES (coupon_seq.NEXTVAL, 'Giảm 10% Giảm tối đa 100,000đ đơn tối thiểu 600,000đ', 0.1, 600000, 100000, 'EGYFGE', 30, 0);


    
INSERT INTO cart_coupon VALUES (cart_coupon_seq.NEXTVAL, 1, 1, 0);
INSERT INTO cart_coupon VALUES (cart_coupon_seq.NEXTVAL, 2, 1, 0);
INSERT INTO cart_coupon VALUES (cart_coupon_seq.NEXTVAL, 3, 3, 0);
INSERT INTO cart_coupon VALUES (cart_coupon_seq.NEXTVAL, 4, 7, 0);
INSERT INTO cart_coupon VALUES (cart_coupon_seq.NEXTVAL, 5, 6, 0);
INSERT INTO cart_coupon VALUES (cart_coupon_seq.NEXTVAL, 6, 9, 0);
INSERT INTO cart_coupon VALUES (cart_coupon_seq.NEXTVAL, 7, 10, 0);
INSERT INTO cart_coupon VALUES (cart_coupon_seq.NEXTVAL, 8, 1, 0);
INSERT INTO cart_coupon VALUES (cart_coupon_seq.NEXTVAL, 9, 1, 0);
INSERT INTO cart_coupon VALUES (cart_coupon_seq.NEXTVAL, 10, 1, 0);


    
INSERT INTO role VALUES (role_seq.NEXTVAL, 'ADMIN', 0);
INSERT INTO role VALUES (role_seq.NEXTVAL, 'USER', 0);
INSERT INTO role VALUES (role_seq.NEXTVAL, 'Quản lý kho', 0);
INSERT INTO role VALUES (role_seq.NEXTVAL, 'Nhân viên bán hàng', 0);
INSERT INTO role VALUES (role_seq.NEXTVAL, 'Thu ngân', 0);
INSERT INTO role VALUES (role_seq.NEXTVAL, 'Quản lý nhân sự', 0);



INSERT INTO user_role VALUES (role_user_seq.NEXTVAL, 1, 1, 0);
INSERT INTO user_role VALUES (role_user_seq.NEXTVAL, 2, 2, 0);
INSERT INTO user_role VALUES (role_user_seq.NEXTVAL, 1, 3, 0);
INSERT INTO user_role VALUES (role_user_seq.NEXTVAL, 1, 4, 0);
INSERT INTO user_role VALUES (role_user_seq.NEXTVAL, 2, 5, 0);
INSERT INTO user_role VALUES (role_user_seq.NEXTVAL, 2, 6, 0);
INSERT INTO user_role VALUES (role_user_seq.NEXTVAL, 3, 7, 0);
INSERT INTO user_role VALUES (role_user_seq.NEXTVAL, 4, 8, 0);
INSERT INTO user_role VALUES (role_user_seq.NEXTVAL, 5, 9, 0);
INSERT INTO user_role VALUES (role_user_seq.NEXTVAL, 6, 10, 0);


    
INSERT INTO function VALUES (function_seq.NEXTVAL, 'Quản lý sản phẩm', 'Thêm, xóa, sửa sản phẩm', 0);
INSERT INTO function VALUES (function_seq.NEXTVAL, 'Quản lý khách hàng', 'Tra cứu các thông tin của khách hàng', 0);
INSERT INTO function VALUES (function_seq.NEXTVAL, 'Phân quyền', 'Giới hạn chức năng cho một ngư�?i dùng', 0);
INSERT INTO function VALUES (function_seq.NEXTVAL, 'Mua hàng', 'Xem và thêm sản phẩm vào gi�? và tiến hành thanh toán', 0);
INSERT INTO function VALUES (function_seq.NEXTVAL, 'Quản lý khuyến mãi', 'Thêm, xóa, sửa khuy?n mãi', 0);
INSERT INTO function VALUES (function_seq.NEXTVAL, 'Quản lý đơn hàng', 'Xem, tra cứu tất cả đơn hàng của cửa hàng', 0);
INSERT INTO function VALUES (function_seq.NEXTVAL, 'Tra cứu sản phẩm', 'Tra cứu thông tin sản phẩm', 0);
INSERT INTO function VALUES (function_seq.NEXTVAL, 'Quản lý kho hàng', 'Thay đổi số lượng sản phẩm đang có trong cửa hàng', 0);
INSERT INTO function VALUES (function_seq.NEXTVAL, 'Thống kê và báo cáo', 'Xem báo cáo kinh doanh cửa cửa hàng', 0);
INSERT INTO function VALUES (function_seq.NEXTVAL, 'Đánh giá sản phẩm', '�?ánh giá sản phẩm', 0);


    
INSERT INTO role_function VALUES (role_function_seq.NEXTVAL, 1, 1, 0);
INSERT INTO role_function VALUES (role_function_seq.NEXTVAL, 1, 2, 0);
INSERT INTO role_function VALUES (role_function_seq.NEXTVAL, 1, 3, 0);
INSERT INTO role_function VALUES (role_function_seq.NEXTVAL, 1, 5, 0);
INSERT INTO role_function VALUES (role_function_seq.NEXTVAL, 1, 6, 0);
INSERT INTO role_function VALUES (role_function_seq.NEXTVAL, 2, 4, 0);
INSERT INTO role_function VALUES (role_function_seq.NEXTVAL, 2, 7, 0);
INSERT INTO role_function VALUES (role_function_seq.NEXTVAL, 3, 8, 0);
INSERT INTO role_function VALUES (role_function_seq.NEXTVAL, 1, 9, 0);
INSERT INTO role_function VALUES (role_function_seq.NEXTVAL, 2, 2, 0);



insert into bill_status(id,status) values(bill_status_seq.NEXTVAL, 'Đang xử lý');
insert into bill_status(id,status) values(bill_status_seq.NEXTVAL, 'Đã xác nhận');
insert into bill_status(id,status) values(bill_status_seq.NEXTVAL, 'Đang vận chuyển');
insert into bill_status(id,status) values(bill_status_seq.NEXTVAL, 'Đã giao hàng');
insert into bill_status(id,status) values(bill_status_seq.NEXTVAL, 'Đã nhận hàng');
insert into bill_status(id,status) values(bill_status_seq.NEXTVAL, 'Đã thanh toán');
insert into bill_status(id,status) values(bill_status_seq.NEXTVAL, 'Đã hủy');



insert into bill(bill_id,customer_id, id_bill_status, date_order,total, note,payment,code_momo,created_at, updated_at)
values(bill_seq.NEXTVAL,1,1,TO_DATE('02/02/2022', 'dd/mm/yyyy'),3000,'Note1','Payment online','ewu',TO_DATE('02/02/2022', 'dd/mm/yyyy'),TO_DATE('02/02/2022', 'dd/mm/yyyy'));

insert into bill(bill_id,customer_id, id_bill_status, date_order,total, note,payment,code_momo,created_at, updated_at)
values(bill_seq.NEXTVAL,2,2,TO_DATE('02/02/2022', 'dd/mm/yyyy'),3000,'Note1','Payment online','ewu',TO_DATE('02/02/2022', 'dd/mm/yyyy'),TO_DATE('02/02/2022', 'dd/mm/yyyy'));

insert into bill(bill_id,customer_id, id_bill_status, date_order,total, note,payment,code_momo,created_at, updated_at)
values(bill_seq.NEXTVAL,1,3,TO_DATE('02/02/2022', 'dd/mm/yyyy'),3600,'Note1','Payment online','ewu',TO_DATE('02/02/2022', 'dd/mm/yyyy'),TO_DATE('02/02/2022', 'dd/mm/yyyy'));

insert into bill(bill_id,customer_id, id_bill_status, date_order,total, note,payment,code_momo,created_at, updated_at)
values(bill_seq.NEXTVAL,2,3,TO_DATE('02/02/2022', 'dd/mm/yyyy'),8000,'Note1','Payment online','ewu',TO_DATE('02/02/2022', 'dd/mm/yyyy'),TO_DATE('02/02/2022', 'dd/mm/yyyy'));

insert into bill(bill_id,customer_id, id_bill_status, date_order,total, note,payment,code_momo,created_at, updated_at)
values(bill_seq.NEXTVAL,3,1,TO_DATE('02/02/2022', 'dd/mm/yyyy'),3700,'Note1','Payment online','ewuewe',TO_DATE('02/02/2022', 'dd/mm/yyyy'),TO_DATE('02/02/2022', 'dd/mm/yyyy'));

insert into bill(bill_id,customer_id, id_bill_status, date_order,total, note,payment,code_momo,created_at, updated_at)
values(bill_seq.NEXTVAL,3,2,TO_DATE('02/02/2022', 'dd/mm/yyyy'),3000,'Note1','Payment online','ewu',TO_DATE('02/02/2022', 'dd/mm/yyyy'),TO_DATE('02/02/2022', 'dd/mm/yyyy'));

insert into bill(bill_id,customer_id, id_bill_status, date_order,total, note,payment,code_momo,created_at, updated_at)
values(bill_seq.NEXTVAL,3,3,TO_DATE('02/02/2022', 'dd/mm/yyyy'),7000,'Note1','Payment online','ewu',TO_DATE('02/02/2022', 'dd/mm/yyyy'),TO_DATE('02/02/2022', 'dd/mm/yyyy'));

insert into bill(bill_id,customer_id, id_bill_status, date_order,total, note,payment,code_momo,created_at, updated_at)
values(bill_seq.NEXTVAL,4,1,TO_DATE('02/02/2022', 'dd/mm/yyyy'),3000,'Note1','Payment online','ewu',TO_DATE('02/02/2022', 'dd/mm/yyyy'),TO_DATE('02/02/2022', 'dd/mm/yyyy'));

insert into bill(bill_id,customer_id, id_bill_status, date_order,total, note,payment,code_momo,created_at, updated_at)
values(bill_seq.NEXTVAL,4,2,TO_DATE('02/02/2022', 'dd/mm/yyyy'),3000,'Note1','Payment online','ewu',TO_DATE('02/02/2022', 'dd/mm/yyyy'),TO_DATE('02/02/2022', 'dd/mm/yyyy'));

insert into bill(bill_id,customer_id, id_bill_status, date_order,total, note,payment,code_momo,created_at, updated_at)
values(bill_seq.NEXTVAL,4,3,TO_DATE('02/02/2022', 'dd/mm/yyyy'),5000,'Note1','Payment online','ewu',TO_DATE('02/02/2022', 'dd/mm/yyyy'),TO_DATE('02/02/2022', 'dd/mm/yyyy'));




    
insert into categories(categories_id,categories_name,image) values(categories_seq.NEXTVAL,'Dụng cụ văn phòng','dungcuvanphong.png');
insert into categories(categories_id,categories_name,image) values(categories_seq.NEXTVAL,'Giấy','giay.jpg');
insert into categories(categories_id,categories_name,image) values(categories_seq.NEXTVAL,'Bìa hồ sơ','biahoso.jpg');
insert into categories(categories_id,categories_name,image) values(categories_seq.NEXTVAL,'Bút viết','but.jpg');
insert into categories(categories_id,categories_name,image) values(categories_seq.NEXTVAL,'Tập sổ','tapso.jpg');
insert into categories(categories_id,categories_name,image) values(categories_seq.NEXTVAL,'Lưu trữ','luutru.jpg');
insert into categories(categories_id,categories_name,image) values(categories_seq.NEXTVAL,'Thiết bị','thietbi.jpg');
insert into categories(categories_id,categories_name,image) values(categories_seq.NEXTVAL,'Gia dụng vệ sinh','giadungvesinh.jpg');
insert into categories(categories_id,categories_name,image) values(categories_seq.NEXTVAL,'Mực in - Ruban','mucinruban.jpg');



insert into product (id, name,type, discount, avgrating, material, category_id, height,width,weight,description,image,brand,made_in,amount, color,price) values
(product_seq.NEXTVAL,'Bấm kim Kanex HD10 - 10 tờ','Kanex', 2000,'','Nhật Bản',1,720,1080,2,'Bấm kim Kanex HD10 - 10 tờ (B-11.6) với thân máy bằng nhựa, dạng bấm, lực bấm 10 tờ, sử dụng kim bấm no.10 liên kết và phân nhóm tài liệu bằng lực bấm hoặc đóng nhiều tờ giấy thành xấp hoặc cuốn phù hợp với nghiệp vụ photocopy và lưu trữ chứng từ liên kết và phân nhóm tài liệu bằng lực bấm hoặc đóng nhiều tờ giấy thành xấp hoặc cuốn phù hợp với nghiệp vụ photocopy và lưu trữ chứng từ ✓Tiết kiệm từ 10% - 30%
Kanex là nhà sản xuất uy tín tại Ấn Độ về sản phẩm máy bấm kim, bấm lổ, kim bấm văn phòng
Sản phẩm Bấm kim chính hãng đáp ứng tiêu chuẩn bảo vệ sức khỏe người dùng và nâng cao tối đa hiệu quả công việc văn phòng mỗi ngày.
Bạn có thể đặt mua nhiều dòng văn phòng phẩm Kanex tại Officexinh.com với mức giá thật ưu đãi và nhận được chính sách vận chuyển miễn phí nếu có dựa trên tổng giá trị đơn hàng và tùy theo khu vực nhận hàng.
Bấm kim Kanex HD10 - 10 tờ còn gọi là bấm ghim, bấm kim số 10 có đơn vị tính là Cái và được đóng gói theo quy cách: 10 cái / lốc
Mẫu mã và thông tin sản phẩm có thể thay đổi theo chính sách nhà sản xuất.','product-1.jpg', 'Kanex','Inda',4000,'Trắng xanh','20000');
insert into product (id, name,type, discount, avgrating, material, category_id, height,width,weight,description,image,brand,made_in,amount, color,price) values
(product_seq.NEXTVAL,'Giấy Double A A4 80','Giấy in', 2000,'','Giấy',2,720,1080,2,'Giấy Double A A4 80 (G-61) với kích thước: A4 (210 mm x 297 mm), định lượng: 80gms, nền giấy trắng in ấn chứng từ, tài liệu, hợp đồng dựa trên khổ giấy chuẩn phù hợp với các nghiệp vụ văn phòng in ấn chứng từ, tài liệu, hợp đồng dựa trên khổ giấy chuẩn phù hợp với các nghiệp vụ văn phòng ✓Tiết kiệm từ 10% - 30%
Double A là hãng sản xuất uy tín tại Thái Lan về sản phẩm giấy photocopy, giấy in, giấy bìa màu
Sản phẩm Giấy in ấn chính hãng đáp ứng tiêu chuẩn bảo vệ sức khỏe người dùng và nâng cao tối đa hiệu quả công việc văn phòng mỗi ngày.
Bạn có thể đặt mua nhiều dòng văn phòng phẩm Double A tại Officexinh.com với mức giá thật ưu đãi và nhận được chính sách vận chuyển miễn phí nếu có dựa trên tổng giá trị đơn hàng và tùy theo khu vực nhận hàng.
Giấy Double A A4 80 còn gọi là giấy photocopy, giấy in, giấy văn phòng có đơn vị tính là Ream và được đóng gói theo quy cách: 5 reams / thùng
Mẫu mã và thông tin sản phẩm có thể thay đổi theo chính sách nhà sản xuất.','product-2.jpg', 'Double A','Thái Lan',4000,'red','93000');

insert into product (id, name,type, discount, avgrating, material, category_id, height,width,weight,description,image,brand,made_in,amount, color,price) values
(product_seq.NEXTVAL,'Bìa còng F4 5cm',' Bìa hồ sơ', 2000,'','Nhựa',3,'','',2,'Bìa còng F4 5cm (loại 1) với độ dày 5 cm, thân bọc simili, dạng còng bật 2 lổ, lưu trữ 300 tờ giấy A4 lưu trữ, phân loại và bảo vệ bề mặt chứng từ giấy có kích thước A4, A5 đã được tạo khoen lổ dọc thân lưu trữ, phân loại và bảo vệ bề mặt chứng từ giấy có kích thước A4, A5 đã được tạo khoen lổ dọc thân ✓Tiết kiệm từ 10% - 30%
Sản phẩm được sản xuất bởi các doanh nghiệp văn phòng phẩm uy tín tại Việt Nam
Sản phẩm Bìa còng chính hãng đáp ứng tiêu chuẩn bảo vệ sức khỏe người dùng và nâng cao tối đa hiệu quả công việc văn phòng mỗi ngày.
Bạn có thể đặt mua nhiều dòng văn phòng phẩm Việt Nam tại Officexinh.com với mức giá thật ưu đãi và nhận được chính sách vận chuyển miễn phí nếu có dựa trên tổng giá trị đơn hàng và tùy theo khu vực nhận hàng.
Bìa còng F4 5cm (loại 1) còn gọi là bìa 2 còng có đơn vị tính là Cái và được đóng gói theo quy cách: 50 cái / thùng
Mẫu mã và thông tin sản phẩm có thể thay đổi theo chính sách nhà sản xuất.','product-3.jpg', 'F4','Việt Nam',4000,'black','7000');
insert into product (id, name,type, discount, avgrating, material, category_id, height,width,weight,description,image,brand,made_in,amount, color,price) values
(product_seq.NEXTVAL,'Bút bi cắm bàn TL','Bút Bi', 2000,'','Nhựa',1,'','',4,'Bút bi cắm bàn TL (B-10.8) với thân nhựa, dạng để bàn, ngòi bi, nét mực êm, cở ngòi 0.5 mm, có dây nối với đế, có lớp keo trống trượt mm, màu mực xanh ghi chú nội dung ,ký kết hợp đồng ghi chú nội dung ,ký kết hợp đồng ✓Tiết kiệm từ 10% - 30%
Thiên Long là thương hiệu hàng đầu tại Việt Nam về sản phẩm bút viết, đồ dùng văn phòng
Sản phẩm Bút bi chính hãng đáp ứng tiêu chuẩn bảo vệ sức khỏe người dùng và nâng cao tối đa hiệu quả công việc văn phòng mỗi ngày.
Bạn có thể đặt mua nhiều dòng văn phòng phẩm Thiên Long tại Officexinh.com với mức giá thật ưu đãi và nhận được chính sách vận chuyển miễn phí nếu có dựa trên tổng giá trị đơn hàng và tùy theo khu vực nhận hàng.
Bút bi cắm bàn TL còn gọi là viết bi, bút mực, viết mực, bút bi bấm có đơn vị tính là Cây Mẫu mã và thông tin sản phẩm có thể thay đổi theo chính sách nhà sản xuất.','product-3.jpg', 'Thiên Long','Việt Nam',4000,'Trắng và xanh','50000');

insert into product (id, name,type, discount, avgrating, material, category_id, height,width,weight,description,image,brand,made_in,amount, color,price) values
(product_seq.NEXTVAL,'Sổ bìa da Vivaone S804','Sổ bìa',5000,'', 'Da',5,17,28,2,'Sổ bìa da Vivaone S804 với kích thước: 17 x 24 cm, bìa bọc simili, có nút gài, nền giấy trắng kẻ ngang, 240 trang ghi chú nội dung chuyên dụng ghi chú nội dung chuyên dụng ✓Tiết kiệm từ 10% - 30%
Sản phẩm được sản xuất bởi các doanh nghiệp văn phòng phẩm uy tín tại Việt Nam
Sản phẩm Sổ tập chính hãng đáp ứng tiêu chuẩn bảo vệ sức khỏe người dùng và nâng cao tối đa hiệu quả công việc văn phòng mỗi ngày.
Bạn có thể đặt mua nhiều dòng văn phòng phẩm Việt Nam tại Officexinh.com với mức giá thật ưu đãi và nhận được chính sách vận chuyển miễn phí nếu có dựa trên tổng giá trị đơn hàng và tùy theo khu vực nhận hàng.
Sổ bìa da Vivaone S804 có đơn vị tính là Cuốn Mẫu mã và thông tin sản phẩm có thể thay đổi theo chính sách nhà sản xuất.','product-4.jpg', 'Vivaone ','Việt Nam',4000,'Nâu','85000');
insert into product (id, name,type, discount, avgrating, material, category_id, height,width,weight,description,image,brand,made_in,amount, color,price) values
(product_seq.NEXTVAL,'Khay đứng 4 ngăn Xukiva 212','Khay', 2000,'','Nhựa',6,'','',2,'Khay đứng 4 ngăn Xukiva 212 (K-32) với dạng xéo, thân vỏ nhựa, 3 ngăn, khả năng lưu trữ 1800 tờ lưu trữ và phân loại tài liệu F4, A4 lưu trữ và phân loại tài liệu F4, A4 ✓Tiết kiệm từ 10% - 30%
Xukiva là nhà sản xuất uy tín tại Việt Nam về sản phẩm đồ dùng văn phòng
Sản phẩm Khay kệ chính hãng đáp ứng tiêu chuẩn bảo vệ sức khỏe người dùng và nâng cao tối đa hiệu quả công việc văn phòng mỗi ngày.
Bạn có thể đặt mua nhiều dòng văn phòng phẩm Xukiva tại Officexinh.com với mức giá thật ưu đãi và nhận được chính sách vận chuyển miễn phí nếu có dựa trên tổng giá trị đơn hàng và tùy theo khu vực nhận hàng.
Khay đứng 4 ngăn Xukiva 212 còn gọi là kệ rỗ, kệ xéo, kệ liên hoàn có đơn vị tính là Cái Mẫu mã và thông tin sản phẩm có thể thay đổi theo chính sách nhà sản xuất.','product-5.jpg', 'Xukiva','Việt Nam',4000,'Xanh','55000');

insert into product (id, name,type, discount, avgrating, material, category_id, height,width,weight,description,image,brand,made_in,amount, color,price) values
(product_seq.NEXTVAL,'Casio AX-120B','Máy tính', 2000,'','Nhựa',7,175,5,2,'Casio AX-120B (G-336) với màn hình 12 số, kích thước: 25(Dày) × 110(Rộng) × 175,5(Dài) mm, phím tính cơ bản, sử dụng năng lượng mặt trời và pin tính toán chuyên dụng đa năng ✓Tiết kiệm từ 10% - 30%
Casio là hãng sản xuất uy tín tại Nhật Bản về sản phẩm máy tính học sinh, máy tính văn phòng
Sản phẩm Máy tính chính hãng đáp ứng tiêu chuẩn bảo vệ sức khỏe người dùng và nâng cao tối đa hiệu quả công việc văn phòng mỗi ngày.
Bạn có thể đặt mua nhiều dòng văn phòng phẩm Casio tại Officexinh.com với mức giá thật ưu đãi và nhận được chính sách vận chuyển miễn phí nếu có dựa trên tổng giá trị đơn hàng và tùy theo khu vực nhận hàng.
Casio AX-120B còn gọi là máy tính bàn, máy tính văn phòng có đơn vị tính là Cái Mẫu mã và thông tin sản phẩm có thể thay đổi theo chính sách nhà sản xuất.','product-6.jpg', 'Casio','Nhật Bản',3000,'Đen xám','290000');

insert into product (id, name,type, discount, avgrating, material, category_id, height,width,weight,description,image,brand,made_in,amount, color,price) values
(product_seq.NEXTVAL,'Nước rửa tay Lifebuoy 493ml','Nước rửa tay', 2000,'','Nước rửa tay',8,'','','','Nước rửa tay Lifebuoy 493ml với dạng lỏng, dung tích 493ml, có vòi xịt, sát khuẩn mạnh, bảo vệ da, hương thơm tự nhiên vệ sinh cơ thể vệ sinh cơ thể ✓Tiết kiệm từ 10% - 30%
Lifebuoy là nhà sản xuất nổi tiếng tại Việt Nam về sản phẩm xà phòng, nước rửa tay, dung dịch tẩy rửa
Sản phẩm Chất tẩy rửa - xịt phòng chính hãng đáp ứng tiêu chuẩn bảo vệ sức khỏe người dùng và nâng cao tối đa hiệu quả công việc văn phòng mỗi ngày.
Bạn có thể đặt mua nhiều dòng văn phòng phẩm Lifebuoy tại Officexinh.com với mức giá thật ưu đãi và nhận được chính sách vận chuyển miễn phí nếu có dựa trên tổng giá trị đơn hàng và tùy theo khu vực nhận hàng.
Nước rửa tay Lifebuoy 493ml còn gọi là xà phòng rửa tay có đơn vị tính là Chai Mẫu mã và thông tin sản phẩm có thể thay đổi theo chính sách nhà sản xuất.','product-7.jpg', 'Lifebuoy','Việt Nam',5000,'Trắng xanh','75000');


    
INSERT INTO rating(id, comment_product, rate_score, id_user, product_id) values(rating_seq.NEXTVAL, 'sản phẩm tốt', 1, 1, 1);
INSERT INTO rating(id, comment_product, rate_score, id_user, product_id) values(rating_seq.NEXTVAL, 'sản phẩm nhanh hỏng', 1, 1, 2);
INSERT INTO rating(id, comment_product, rate_score, id_user, product_id) values(rating_seq.NEXTVAL, 'giá tốt', 1, 1, 3);
INSERT INTO rating(id, comment_product, rate_score, id_user, product_id) values(rating_seq.NEXTVAL, 'sản phẩm tốt trong tầm giá', 1, 1, 4);
INSERT INTO rating(id, comment_product, rate_score, id_user, product_id) values(rating_seq.NEXTVAL, 'Best OF the Year', 1, 1, 5);
INSERT INTO rating(id, comment_product, rate_score, id_user, product_id) values(rating_seq.NEXTVAL, 'Dùng nhanh hỏng', 1, 1, 6);
INSERT INTO rating(id, comment_product, rate_score, id_user, product_id) values(rating_seq.NEXTVAL, 'Giá tốt', 1, 1, 7);
INSERT INTO rating(id, comment_product, rate_score, id_user, product_id) values(rating_seq.NEXTVAL, 'World Class', 1, 1, 1);
INSERT INTO rating(id, comment_product, rate_score, id_user, product_id) values(rating_seq.NEXTVAL, 'Comment1', 1, 1, 1);
INSERT INTO rating(id, comment_product, rate_score, id_user, product_id) values(rating_seq.NEXTVAL, 'Comment1', 1, 1, 1);


    
insert into cart_product(id,cart_id,product_id,quantity) values(cart_product_seq.NEXTVAL,1,1,10);
insert into cart_product(id,cart_id,product_id,quantity) values(cart_product_seq.NEXTVAL,2,1,10);
insert into cart_product(id,cart_id,product_id,quantity) values(cart_product_seq.NEXTVAL,2,2,10);
insert into cart_product(id,cart_id,product_id,quantity) values(cart_product_seq.NEXTVAL,4,2,10);
insert into cart_product(id,cart_id,product_id,quantity) values(cart_product_seq.NEXTVAL,3,3,10);
insert into cart_product(id,cart_id,product_id,quantity) values(cart_product_seq.NEXTVAL,3,3,10);
insert into cart_product(id,cart_id,product_id,quantity) values(cart_product_seq.NEXTVAL,2,5,10);
insert into cart_product(id,cart_id,product_id,quantity) values(cart_product_seq.NEXTVAL,2,3,10);
insert into cart_product(id,cart_id,product_id,quantity) values(cart_product_seq.NEXTVAL,2,4,10);


    
insert into bill_detail(id, quantity, price, id_bill, id_product) 
values(bill_detail_seq.NEXTVAL, 2, 30000, 1, 1);
insert into bill_detail(id, quantity, price, id_bill, id_product) 
values(bill_detail_seq.NEXTVAL, 4, 60000, 2, 1);
insert into bill_detail(id, quantity, price, id_bill, id_product) 
values(bill_detail_seq.NEXTVAL, 2, 150000, 2, 2);
insert into bill_detail(id, quantity, price, id_bill, id_product) 
values(bill_detail_seq.NEXTVAL, 5, 100000, 3, 1);
insert into bill_detail(id, quantity, price, id_bill, id_product) 
values(bill_detail_seq.NEXTVAL, 4, 40000, 4, 4);
insert into bill_detail(id, quantity, price, id_bill, id_product) 
values(bill_detail_seq.NEXTVAL, 1, 12000, 5, 4);
insert into bill_detail(id, quantity, price, id_bill, id_product) 
values(bill_detail_seq.NEXTVAL, 4, 135000, 6, 4);
insert into bill_detail(id, quantity, price, id_bill, id_product) 
values(bill_detail_seq.NEXTVAL, 5, 136500, 7, 3);
insert into bill_detail(id, quantity, price, id_bill, id_product) 
values(bill_detail_seq.NEXTVAL, 1, 12000, 1, 7);
insert into bill_detail(id, quantity, price, id_bill, id_product) 
values(bill_detail_seq.NEXTVAL, 8, 147000, 8, 7);



