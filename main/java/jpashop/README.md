# JPAshop
---

### 데이터 중심 설계의 문제점
- 현재 방식은 객체 설계를 테이블 설계에 맞춘 방식
- 테이블의 외래키를 객체에 그대로 가져옴
- 객체 그래프 탐색이 불가능
- 참조가 없으므로 UML도 잘못됨

## TABLE 설계
---
![TABLE.png](images/img.png)

## Entity 설계와 매핑
---
![Entity.png](images/img_1.png)

## CREATE Entity result
```shell
Hibernate: 
    
    create table Item (
       ITEM_ID bigint not null,
        name varchar(255),
        price integer not null,
        stackQuantity integer not null,
        primary key (ITEM_ID)
    )
Hibernate: 
    
    create table Member (
       MEMBER_ID bigint not null,
        city varchar(255),
        name varchar(255),
        street varchar(255),
        zipcode varchar(255),
        primary key (MEMBER_ID)
    )
Hibernate: 
    
    create table OrderItem (
       ORDER_ITEM_ID bigint not null,
        count integer not null,
        ITEM_ID bigint,
        ORDER_ID bigint,
        orderPrice integer not null,
        primary key (ORDER_ITEM_ID)
    )
Hibernate: 
    
    create table ORDERS (
       ORDER_ID bigint not null,
        MEMBER_ID bigint,
        orderDate timestamp,
        status varchar(255),
        primary key (ORDER_ID)
    )

```