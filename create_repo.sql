CREATE table smbms_repo
(
    id           int PRIMARY KEY, -- 主键  后期要和bill的添加相等的多(看是否  能添加) 余量是否够.
    total        DECIMAL(20, 2),  -- 商品剩余数量
    -- 这里后期用total=total-bill.productCount
    unit         varchar(10),-- productUnit 和bill一样.bill不能改了弄好先.
    providerName VARCHAR(20),-- 供应商名字
    providerCode varchar(20)      -- 供应商编号
);
use smnbms_repo ;
insert into smbms_repo value (3, 2500, '斤', (select proName from smbms_provider where smbms_provider.id = 6),(select proCode from smbms_provider where smbms_provider.id = 6));
insert into smbms_repo value (5, 2500, '瓶', (select proName from smbms_provider where smbms_provider.id = 9),(select proCode from smbms_provider where smbms_provider.id = 9));
insert into smbms_repo value (6, 2500, '袋', (select proName from smbms_provider where smbms_provider.id = 4),(select proCode from smbms_provider where smbms_provider.id = 4));
insert into smbms_repo value (7, 2500, '瓶', (select proName from smbms_provider where smbms_provider.id = 14),(select proCode from smbms_provider where smbms_provider.id = 14));
insert into smbms_repo value (null, 2500, '个', (select proName from smbms_provider where smbms_provider.id = 14),(select proCode from smbms_provider where smbms_provider.id = 14));
insert into smbms_repo value (null, 2500, '个', (select proName from smbms_provider where smbms_provider.id = 14),(select proCode from smbms_provider where smbms_provider.id = 14));
insert into smbms_repo value (null, 2500, '瓶', (select proName from smbms_provider where smbms_provider.id = 8),(select proCode from smbms_provider where smbms_provider.id = 8));
insert into smbms_repo value (null, 2500, '瓶', (select proName from smbms_provider where smbms_provider.id = 1),(select proCode from smbms_provider where smbms_provider.id = 1));
insert into smbms_repo value (12, 2500, '瓶', (select proName from smbms_provider where smbms_provider.id = 1),(select proCode from smbms_provider where smbms_provider.id = 1));
insert into smbms_repo value (13, 2500, '瓶', (select proName from smbms_provider where smbms_provider.id = 1),(select proCode from smbms_provider where smbms_provider.id = 1));
insert into smbms_repo value (14, 2500, '斤', (select proName from smbms_provider where smbms_provider.id = 3),(select proCode from smbms_provider where smbms_provider.id = 3));
insert into smbms_repo value (15, 2500, '斤', (select proName from smbms_provider where smbms_provider.id = 3),(select proCode from smbms_provider where smbms_provider.id = 3));
insert into smbms_repo value (16, 2500, '瓶', (select proName from smbms_provider where smbms_provider.id = 2),(select proCode from smbms_provider where smbms_provider.id = 2));
insert into smbms_repo value (17, 2500, '瓶', (select proName from smbms_provider where smbms_provider.id = 2),(select proCode from smbms_provider where smbms_provider.id = 2));
insert into smbms_repo value (18, 2500, '瓶', (select proName from smbms_provider where smbms_provider.id = 2),(select proCode from smbms_provider where smbms_provider.id = 2));
insert into smbms_repo value (19, 2500, '个', (select proName from smbms_provider where smbms_provider.id = 2),(select proCode from smbms_provider where smbms_provider.id = 2));
