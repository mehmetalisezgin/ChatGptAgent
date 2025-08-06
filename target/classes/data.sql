-- Sample initial data for the resume web application.
-- This file is executed on application startup when using the default
-- configuration. You can modify or remove these entries as you update
-- the application with your own information.

-- About Me
INSERT INTO about_me (id, content) VALUES
  (1, 'Merhaba, ben Kod Kahvesi. Full stack development ve yapay zekâ alanlarında uzmanlaşmış bir yazılım geliştiricisiyim. Java ve modern web teknolojileri ile ölçeklenebilir uygulamalar geliştiriyorum.');

-- Experiences
INSERT INTO experiences (company_name, position, year_start, year_end) VALUES
  ('Tech Solutions Ltd.', 'Full Stack Developer', 2022, 2024),
  ('Creative Labs', 'Backend Developer', 2020, 2022),
  ('Freelance', 'Software Developer', 2018, 2020);

-- Projects
INSERT INTO projects (title, description, github_link) VALUES
  ('Öğrenci Yönetim Sistemi', 'Spring Boot ve Thymeleaf kullanarak geliştirilmiş okul yönetim sistemi. Öğrenci ekleme, güncelleme ve listeleme özellikleri içerir.', 'https://github.com/example/student-management-system'),
  ('Restoran Rezervasyon Uygulaması', 'Masa rezervasyonu için geliştirilen web uygulaması. Kullanıcılar rezervasyon oluşturabilir, yönetici paneli üzerinden rezervasyonlar görüntülenebilir.', 'https://github.com/example/restaurant-reservation-app');

-- Tools
INSERT INTO tools (tool_name) VALUES
  ('Java'),
  ('Spring Boot'),
  ('Selenium'),
  ('HTML'),
  ('CSS'),
  ('Git'),
  ('TestNG'),
  ('JUnit'),
  ('Postman'),
  ('Maven'),
  ('Docker'),
  ('MySQL');