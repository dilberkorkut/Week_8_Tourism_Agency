

-- Tablo döküm verisi `users`
--

INSERT INTO `users` (`id`, `name`, `uname`, `password`, `type`) VALUES
(1, 'Dilber Korkut Bilgin', 'dilber', '123', 'agency'),
(2, 'Deniz Bilgin', 'deniz', '123', 'admin'),
(7, 'd', 'd', 'd', 'admin');



- Tablo döküm verisi `hostel_types`
  --

INSERT INTO `hostel_types` (`id`, `hotel_id`, `hostel_types`) VALUES
(1, 1, 'Bed and Breakfast'),
(2, 1, 'Half Board'),
(3, 2, 'All-Inclusive'),
(4, 2, 'Room Only'),
(5, 3, 'Ultra All-Inclusive');

-- Tablo döküm verisi `hotels`
--

INSERT INTO `hotels` (`id`, `name`, `city`, `region`, `address`, `email`, `phone`, `star_rate`, `facility_features`, `hostel_types`) VALUES
(1, 'Kodluyoruz Life Istanbul', 'Istanbul', 'Beyoglu', 'Sahkulu, Sishane Metro Duragi', 'info@kodluyoruz.org', '02121112233', '5', 'Free Parking SPA 7/24 Room Service ', 'Bed and Breakfast Half Board '),
(2, 'Patika Plus Hotel', 'Istanbul', 'Nisantasi', 'Tesvikiye mah. Tesvikiye Cami Arkasi', 'info@patikaplus.org', '02121112233', '4', 'Free Wi-Fi SPA ', 'All-Inclusive Room Only '),
(3, 'Hilton ', 'Ankara', 'Kizilay', 'Kugulupark Cankaya', 'info@hilton.org', '05301112233', '5', 'Free Wi-Fi Hotel Concierge ', 'Ultra All-Inclusive ');

-- Tablo döküm verisi `hotel_seasons`
--

INSERT INTO `hotel_seasons` (`id`, `hotel_id`, `start_date`, `end_date`, `season`) VALUES
(1, 1, '01/01/2024', '31/05/2024', '01/01/2024-31/05/2024'),
(3, 1, '01/06/2024', '31/12/2024', '01/06/2024-31/12/2024'),
(4, 2, '01/01/2024', '31/05/2024', '01/01/2024-31/05/2024'),
(6, 2, '01/06/2024', '31/12/2024', '01/06/2024-31/12/2024'),
(7, 3, '10/02/2025', '20/06/2025', '10/02/2025-20/06/2025'),
(8, 3, '21/06/2025', '31/06/2025', '21/06/2025-31/06/2025');



-- Tablo döküm verisi `prices`
--

INSERT INTO `prices` (`id`, `hotel_id`, `room_id`, `season_id`, `start_date`, `end_date`, `season`, `hostel_id`, `hostel_types`, `room_type`, `age`, `price`) VALUES
(1, 1, 1, 1, '01/01/2024', '31/05/2024', '01/01/2024-31/05/2024', 1, 'Bed and Breakfast', 'single', 'Adult 12+', '1000'),
(2, 1, 2, 1, '01/01/2024', '31/05/2024', '01/01/2024-31/05/2024', 1, 'Bed and Breakfast', 'double', 'Adult 12+', '2000'),
(3, 1, 3, 1, '01/01/2024', '31/05/2024', '01/01/2024-31/05/2024', 1, 'Bed and Breakfast', 'suit', 'Adult 12+', '3000'),
(4, 1, 1, 1, '01/01/2024', '31/05/2024', '01/01/2024-31/05/2024', 1, 'Bed and Breakfast', 'single', 'Child 4-11', '1000'),
(5, 1, 2, 1, '01/01/2024', '31/05/2024', '01/01/2024-31/05/2024', 1, 'Bed and Breakfast', 'double', 'Child 4-11', '2000'),
(6, 1, 3, 1, '01/01/2024', '31/05/2024', '01/01/2024-31/05/2024', 1, 'Bed and Breakfast', 'suit', 'Child 4-11', '3000'),
(7, 1, 1, 1, '01/01/2024', '31/05/2024', '01/01/2024-31/05/2024', 2, 'Half Board', 'single', 'Adult 12+', '1000'),
(8, 1, 2, 1, '01/01/2024', '31/05/2024', '01/01/2024-31/05/2024', 1, 'Bed and Breakfast', 'double', 'Adult 12+', '2000'),
(9, 1, 3, 1, '01/01/2024', '31/05/2024', '01/01/2024-31/05/2024', 1, 'Bed and Breakfast', 'suit', 'Adult 12+', '3000'),
(10, 1, 1, 1, '01/01/2024', '31/05/2024', '01/01/2024-31/05/2024', 1, 'Bed and Breakfast', 'single', 'Child 4-11', '1000'),
(11, 1, 2, 1, '01/01/2024', '31/05/2024', '01/01/2024-31/05/2024', 1, 'Bed and Breakfast', 'double', 'Child 4-11', '2000'),
(12, 1, 3, 1, '01/01/2024', '31/05/2024', '01/01/2024-31/05/2024', 1, 'Bed and Breakfast', 'suit', 'Child 4-11', '3000'),
(13, 1, 1, 3, '01/06/2024', '31/12/2024', '01/06/2024-31/12/2024', 2, 'Half Board', 'single', 'Adult 12+', '1000'),
(14, 1, 2, 1, '01/01/2024', '31/05/2024', '01/01/2024-31/05/2024', 1, 'Bed and Breakfast', 'double', 'Adult 12+', '2000'),
(15, 1, 3, 1, '01/01/2024', '31/05/2024', '01/01/2024-31/05/2024', 1, 'Bed and Breakfast', 'suit', 'Adult 12+', '3000'),
(16, 1, 1, 1, '01/01/2024', '31/05/2024', '01/01/2024-31/05/2024', 1, 'Bed and Breakfast', 'single', 'Child 4-11', '1000'),
(17, 1, 2, 1, '01/01/2024', '31/05/2024', '01/01/2024-31/05/2024', 1, 'Bed and Breakfast', 'double', 'Child 4-11', '2000'),
(18, 1, 3, 1, '01/01/2024', '31/05/2024', '01/01/2024-31/05/2024', 1, 'Bed and Breakfast', 'suit', 'Child 4-11', '3000'),
(19, 2, 4, 4, '01/01/2024', '31/05/2024', '01/01/2024-31/05/2024', 3, 'All-Inclusive', 'single', 'Adult 12+', '1000'),
(22, 2, 5, 4, '01/01/2024', '31/05/2024', '01/01/2024-31/05/2024', 3, 'All-Inclusive', 'double', 'Adult 12+', '2000'),
(23, 2, 6, 4, '01/01/2024', '31/05/2024', '01/01/2024-31/05/2024', 3, 'All-Inclusive', 'suit', 'Adult 12+', '3000'),
(24, 2, 4, 4, '01/01/2024', '31/05/2024', '01/01/2024-31/05/2024', 3, 'All-Inclusive', 'single', 'Child 4-11', '1000'),
(25, 2, 5, 4, '01/01/2024', '31/05/2024', '01/01/2024-31/05/2024', 3, 'All-Inclusive', 'double', 'Child 4-11', '2000'),
(26, 2, 6, 4, '01/01/2024', '31/05/2024', '01/01/2024-31/05/2024', 3, 'All-Inclusive', 'suit', 'Child 4-11', '3000'),
(27, 3, 9, 8, '21/06/2025', '31/06/2025', '21/06/2025-31/06/2025', 5, 'Ultra All-Inclusive', 'single', 'Adult 12+', '1000'),
(28, 3, 8, 8, '21/06/2025', '31/06/2025', '21/06/2025-31/06/2025', 5, 'Ultra All-Inclusive', 'suit', 'Adult 12+', '3000'),
(29, 3, 7, 8, '21/06/2025', '31/06/2025', '21/06/2025-31/06/2025', 5, 'Ultra All-Inclusive', 'double', 'Adult 12+', '2000');


-- Tablo döküm verisi `reservations`
--

INSERT INTO `reservations` (`id`, `hotel_id`, `room_type`, `client_name`, `client_tc_passport`, `phone`, `email`, `check_in`, `check_out`, `days`, `adult_number`, `child_number`, `hostel_types`, `total_price`, `room_id`) VALUES
(1, 1, 'single', 'Dilber Korkut Bilgin', '12345678910', '05361112233', 'dilberkrkt@gmail.com', '02/04/2024', '04/04/2024', '2', 2, 1, 'Bed and Breakfast', 6000, 1),
(2, 2, 'suit', 'Ali Simsek', '12345678901', '05301112233', 'alisimsek@patika', '01/02/2024', '03/02/2024', '2', 1, 1, 'All-Inclusive', 12000, 6),
(3, 3, 'suit', 'Deniz Ural', '12345678910', '05301112233', 'denizural@patika', '22/06/2025', '24/06/2025', '2', 1, 1, 'Ultra All-Inclusive', 6000, 8),
(4, 3, 'suit', 'Ozge Acik', '12345678901', '05321001122', 'ozgeacik@patika', '22/06/2025', '24/06/2025', '2', 1, 1, 'Ultra All-Inclusive', 6000, 8);

-- Tablo döküm verisi `rooms`
--

INSERT INTO `rooms` (`id`, `hotel_id`, `room_type`, `stock`, `beds`, `tv`, `minibar`) VALUES
(1, 1, 'single', '9', '1', 'yes', 'yes'),
(2, 1, 'double', '10', '2', 'yes', 'yes'),
(3, 1, 'suit', '10', '3', 'yes', 'yes'),
(4, 2, 'single', '10', '1', 'yes', 'yes'),
(5, 2, 'double', '10', '2', 'yes', 'yes'),
(6, 2, 'suit', '9', '3', 'yes', 'yes'),
(7, 3, 'double', '10', '2', 'yes', 'yes'),
(8, 3, 'suit', '8', '3', 'yes', 'yes'),
(9, 3, 'single', '10', '1', 'yes', 'yes');


