# Поиск аэропортов
 Java-приложение, позволяющее быстро искать данные аэропортов по вводимому пользователем названию аэропорта и фильтрам.
Данные для программы берутся из файла. В нем находится таблица аэропортов со свойствами в формате CSV. Название аэропорта — 2 колонка. 
За что отвечают другие колонки — не важно, на них навешиваются фильтры. 
Фильтры могут быть — отношения равенства: равно ,не равно , больше, меньше. 
Фильтр передается в формате: 
column[1]>10 & column[5]=’GKA’ || column[<номер колонки с 1>]<операция сравнения>... 
Фильтры могут соединяться отношением И и ИЛИ. 
Также могут участвовать скобки для обозначения приоритета и группировки.
