CREATE DATABASE Qlkytucxa;
GO
USE Qlkytucxa;
GO
CREATE TABLE KhuNha (
    MaKhu VARCHAR(10) PRIMARY KEY,
    TenKhu NVARCHAR(50) NOT NULL,
    LoaiKhu NVARCHAR(20) CHECK (LoaiKhu IN (N'Nam', N'Nữ', N'Hỗn hợp')),
    GhiChu NVARCHAR(255)
);

CREATE TABLE LoaiPhong (
    MaLoai VARCHAR(10) PRIMARY KEY,
    TenLoai NVARCHAR(50) NOT NULL,
    DienTich FLOAT,
    SoGiuong INT NOT NULL CHECK (SoGiuong > 0),
    GiaPhong DECIMAL(18, 2) NOT NULL CHECK (GiaPhong >= 0),
    MoTa NVARCHAR(255)
);
CREATE TABLE Phong (
    MaPhong VARCHAR(10) PRIMARY KEY,
    MaKhu VARCHAR(10) FOREIGN KEY REFERENCES KhuNha(MaKhu) ON DELETE CASCADE,
    MaLoai VARCHAR(10) FOREIGN KEY REFERENCES LoaiPhong(MaLoai),
    TenPhong NVARCHAR(50) NOT NULL,
    SoNguoiHienTai INT DEFAULT 0,
    TrangThai NVARCHAR(30) DEFAULT N'Bình thường' CHECK (TrangThai IN (N'Bình thường', N'Đang sửa chữa', N'Tạm ngưng sử dụng'))
);
CREATE TABLE Giuong (
    MaGiuong VARCHAR(15) PRIMARY KEY, 
    MaPhong VARCHAR(10) FOREIGN KEY REFERENCES Phong(MaPhong) ON DELETE CASCADE,
    SoThuTu INT NOT NULL,
    TrangThai NVARCHAR(30) DEFAULT N'Trống' CHECK (TrangThai IN (N'Trống', N'Đã thuê', N'Hỏng'))
);
CREATE TABLE NhanVien (
    MaNV VARCHAR(20) PRIMARY KEY,
    HoTen NVARCHAR(100) NOT NULL,
    ChucVu NVARCHAR(50),
    SoDienThoai VARCHAR(15) UNIQUE,
    Email VARCHAR(100) UNIQUE,
    TaiKhoan VARCHAR(50) UNIQUE NOT NULL,
    MatKhau VARCHAR(255) NOT NULL, 
    TrangThai BIT DEFAULT 1 
);

CREATE TABLE SinhVien (
    MaSV VARCHAR(20) PRIMARY KEY,
    HoTen NVARCHAR(100) NOT NULL,
    NgaySinh DATE,
    GioiTinh NVARCHAR(10) CHECK (GioiTinh IN (N'Nam', N'Nữ')),
    CCCD VARCHAR(20) UNIQUE NOT NULL,
    SoDienThoai VARCHAR(15) UNIQUE,
    Email VARCHAR(100),
    TruongHoc NVARCHAR(100),
    QueQuan NVARCHAR(255),
    HoTenNguoiThan NVARCHAR(100),
    SDTNguoiThan VARCHAR(15),
    TrangThai NVARCHAR(30) DEFAULT N'Đang lưu trú' CHECK (TrangThai IN (N'Chờ duyệt', N'Đang lưu trú', N'Đã rời đi', N'Bị đuổi'))
);

CREATE TABLE HopDong (
    MaHD INT IDENTITY(1,1) PRIMARY KEY,
    MaSV VARCHAR(20) FOREIGN KEY REFERENCES SinhVien(MaSV),
    MaGiuong VARCHAR(15) FOREIGN KEY REFERENCES Giuong(MaGiuong),
    MaNVLap VARCHAR(20) FOREIGN KEY REFERENCES NhanVien(MaNV),
    NgayLap DATETIME DEFAULT GETDATE(),
    NgayBatDau DATE NOT NULL,
    NgayKetThuc DATE NOT NULL,
    TienCoc DECIMAL(18, 2) DEFAULT 0,
    TrangThai NVARCHAR(30) DEFAULT N'Đang hiệu lực' CHECK (TrangThai IN (N'Chưa bắt đầu', N'Đang hiệu lực', N'Đã thanh lý', N'Đã hủy')),
    CONSTRAINT CHK_NgayHopDong CHECK (NgayKetThuc > NgayBatDau)
);

CREATE TABLE ChiSoDienNuoc (
    MaCS INT IDENTITY(1,1) PRIMARY KEY,
    MaPhong VARCHAR(10) FOREIGN KEY REFERENCES Phong(MaPhong),
    Thang INT NOT NULL CHECK (Thang BETWEEN 1 AND 12),
    Nam INT NOT NULL,
    SoDienCu INT NOT NULL CHECK (SoDienCu >= 0),
    SoDienMoi INT NOT NULL,
    SoNuocCu INT NOT NULL CHECK (SoNuocCu >= 0),
    SoNuocMoi INT NOT NULL,
    MaNVGhi VARCHAR(20) FOREIGN KEY REFERENCES NhanVien(MaNV),
    NgayGhi DATE DEFAULT GETDATE(),
    CONSTRAINT CHK_SoDien CHECK (SoDienMoi >= SoDienCu),
    CONSTRAINT CHK_SoNuoc CHECK (SoNuocMoi >= SoNuocCu),
    CONSTRAINT UQ_ChiSo UNIQUE (MaPhong, Thang, Nam)
);

CREATE TABLE HoaDon (
    MaHD INT IDENTITY(1,1) PRIMARY KEY,
    MaPhong VARCHAR(10) FOREIGN KEY REFERENCES Phong(MaPhong),
    MaNVLap VARCHAR(20) FOREIGN KEY REFERENCES NhanVien(MaNV),
    Thang INT NOT NULL,
    Nam INT NOT NULL,
    NgayLap DATETIME DEFAULT GETDATE(),
    HanThanhToan DATE NOT NULL,
    TongTien DECIMAL(18, 2) NOT NULL DEFAULT 0,
    TrangThai NVARCHAR(30) DEFAULT N'Chưa thanh toán' CHECK (TrangThai IN (N'Chưa thanh toán', N'Đã thanh toán một phần', N'Đã thanh toán xong')),
    GhiChu NVARCHAR(255)
);

CREATE TABLE ChiTietHoaDon (
    MaCTHD INT IDENTITY(1,1) PRIMARY KEY,
    MaHoaDon INT FOREIGN KEY REFERENCES HoaDon(MaHD) ON DELETE CASCADE,
    TenLoaiPhi NVARCHAR(100) NOT NULL, 
    SoLuong FLOAT DEFAULT 1,
    DonGia DECIMAL(18, 2) NOT NULL,
    ThanhTien AS (SoLuong * DonGia) PERSISTED 
);


CREATE TABLE ThanhToan (
    MaTT INT IDENTITY(1,1) PRIMARY KEY,
    MaHoaDon INT FOREIGN KEY REFERENCES HoaDon(MaHD),
    MaSV VARCHAR(20) FOREIGN KEY REFERENCES SinhVien(MaSV), 
    MaNVThu VARCHAR(20) FOREIGN KEY REFERENCES NhanVien(MaNV),
    NgayThanhToan DATETIME DEFAULT GETDATE(),
    SoTienNop DECIMAL(18, 2) NOT NULL CHECK (SoTienNop > 0),
    PhuongThuc NVARCHAR(50) CHECK (PhuongThuc IN (N'Tiền mặt', N'Chuyển khoản', N'Quẹt thẻ')),
    GhiChu NVARCHAR(255)
);

CREATE TABLE YeuCauSuaChua (
    MaYC INT IDENTITY(1,1) PRIMARY KEY,
    MaPhong VARCHAR(10) FOREIGN KEY REFERENCES Phong(MaPhong),
    MaSV VARCHAR(20) FOREIGN KEY REFERENCES SinhVien(MaSV), 
    ThietBiHong NVARCHAR(100) NOT NULL,
    MoTaChiTiet NVARCHAR(MAX),
    NgayBao DATETIME DEFAULT GETDATE(),
    TrangThai NVARCHAR(30) DEFAULT N'Chờ tiếp nhận' CHECK (TrangThai IN (N'Chờ tiếp nhận', N'Đang xử lý', N'Đã hoàn thành', N'Từ chối')),
    NgayHoanThanh DATETIME,
    ChiPhiSuaChua DECIMAL(18,2) DEFAULT 0,
    GhiChu NVARCHAR(255)
);

CREATE TABLE KyLuat (
    MaKL INT IDENTITY(1,1) PRIMARY KEY,
    MaSV VARCHAR(20) FOREIGN KEY REFERENCES SinhVien(MaSV),
    MaNVLap VARCHAR(20) FOREIGN KEY REFERENCES NhanVien(MaNV),
    Loai NVARCHAR(20) CHECK (Loai IN (N'Khen thưởng', N'Kỷ luật')),
    HinhThuc NVARCHAR(100) NOT NULL, 
    NoiDung NVARCHAR(MAX) NOT NULL,
    NgayQuyetDinh DATETIME DEFAULT GETDATE(),
    TienPhat DECIMAL(18, 2) DEFAULT 0
);
GO
INSERT INTO NhanVien (MaNV, HoTen, ChucVu, SoDienThoai, Email, TaiKhoan, MatKhau, TrangThai) VALUES
('NV01', N'Quản trị viên', N'Quản lý', '0900000001', 'admin@ktx.vn', 'admin', '123456', 1)
INSERT INTO NhanVien (MaNV, HoTen, ChucVu, SoDienThoai, Email, TaiKhoan, MatKhau, TrangThai) VALUES
('NV02', N'Nhân viên thu ngân', N'Nhân viên', '0900000002', 'nv02@ktx.vn', 'nhanvien', '1234', 1);
INSERT INTO KhuNha (MaKhu, TenKhu, LoaiKhu, GhiChu) VALUES
('A', N'Khu A', N'Nam', N'Khu nhà nam'),
('B', N'Khu B', N'Nữ', N'Khu nhà nữ');

INSERT INTO LoaiPhong (MaLoai, TenLoai, DienTich, SoGiuong, GiaPhong, MoTa) VALUES
('L4', N'Phòng 4 người', 24, 4, 600000, N'Phòng 4 giường, có quạt'),
('L6', N'Phòng 6 người', 36, 6, 450000, N'Phòng 6 giường');

INSERT INTO Phong (MaPhong, MaKhu, MaLoai, TenPhong) VALUES
('A101', 'A', 'L4', N'Phòng A101'),
('A102', 'A', 'L6', N'Phòng A102'),
('B101', 'B', 'L4', N'Phòng B101');

INSERT INTO Giuong (MaGiuong, MaPhong, SoThuTu) VALUES
('A101-G1', 'A101', 1),
('A101-G2', 'A101', 2),
('A101-G3', 'A101', 3),
('A101-G4', 'A101', 4),
('A102-G1', 'A102', 1),
('A102-G2', 'A102', 2),
('A102-G3', 'A102', 3),
('A102-G4', 'A102', 4),
('A102-G5', 'A102', 5),
('A102-G6', 'A102', 6),
('B101-G1', 'B101', 1),
('B101-G2', 'B101', 2),
('B101-G3', 'B101', 3),
('B101-G4', 'B101', 4);