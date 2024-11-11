# java-convenience-store-precourse

# 4차 미션

구매자의 할인 혜택과 재고 상황을 고려하여 최종 결제 금액을 계산하고 안내하는 결제 시스템
- 사용자가 입력한 상품의 가격과 수량을 기반으로 최종 결제 금액을 계산한다.
    - 충구매액 :  **상품별 가격과 수량을 곱하여 계산하며**
    - 최종 결제 금액 :  프로모션 및 멤버십 할인 정책을 반영하여
- 구매 내역과 산출한 금액 정보를 영수증으로 출력한다
- 영**수증 출력 후 추가 구매를 진행할지 또는 종료할지를 선택할 수 있다**

<br>


<br>

### **재고 관리**

---



- [x]  프로모션과, 프로모션 아닐때에 따라서 재고관리를 다르게 한다.
    - 고객이 상품을 구매 완료 → 결제된 수량만큼 해당 상품의 재고에서 차감한다
    - 시스템은 항상 최신 재고 상태를 유지한다
    - 재고는 프로모션 재고와, 일반재고로 분리한다
    - 프로모션 상황일때는, 프로모션 재고를 먼저 감소한다
    - 일반 상황일 때는, 일반재고를 먼저 감소하고 프로모션 재고를 사용한다

<br>

### 상품
- [x] 상품을 이름으로 찾아온다


<br>


### 주문

---


- [x] 주문을 생성한다
    - 스스로 무료 아이템은 얼마인지, 프로모션 기간인지 판단하도록 한다.
    - 오늘 날짜가 프로모션 기간에 포함된지 확인한다
    - 프로모션 혜택이 있는 상품인지 확인한다
    - 프로모션의 재고가 0개 이상이어야한다
    - 현재 가져온 것들 중 프로모션 적용 가능 수 많큼, freeQuantity 수량을 초기화한다

<br>

- [x] 주문을 받아서 생성한다
  - 사용자가 주문한 내용을 기준으로 주문을 생성된다
  - 주문 내역의 수량과 이름을 기준으로 올바른 주문인지 확인한다

<br>

- [x] 받아온 주문을 파싱한다
    - 사용자에게서 주문을 받아와서, 그 주문을 형식에 맞게 포매팅한다
    - 입력을 올바르게 했는지 확인한다

<br>

- [x] 주문을 진행한다
    - 사용자에게 발행한 주문을 기준으로 주문을 진행한다
    - 프로모션 중인 주문이라면, 사용자의 최대 프로모션 적용 가능을 확인한다
    - 프로모션중인 대상이라면, 사용자가 올바른 수량을 다 가져왔는지 확인한다
    - 프로모션 재고가 부족 → 일부 수량을 프로모션 혜택 없이 결제해야 하는 경우 일부 수량에 대해 정가로 결제할지 여부를 입력받는다.
        - Y: 일부 수량에 대해 정가로 결제한다.
        - N: 정가로 결제해야하는 수량만큼 제외한 후 결제를 진행한다.
  - 프로모션 적용이 가능한 상품에 대해, 고객이 해당 수량보다 적게 가져온 경우, 그 수량만큼 **추가 여부를 입력 받는다**
    - Y: 증정 받을 수 있는 상품을 추가한다.
    - N: 증정 받을 수 있는 상품을 추가하지 않는다.
    
    <br>

- [x]  상품이 프로모션 기간이라면, 프로모션 재고를 먼저 차감한다
    - 프로모션 재고가 부족할 경우에는 일반 재고를 사용한다
    - 반대일 경우, 일반재고를 먼저 차감하고 프로모션 재고를 사용한다

<br>

### 프로모션

---
- [x] 프로모션 기간인지 확인한다
- [x] 프로모션 재고를 한도로, 사용자가 놓친 혜택이 있는지 계산한다
- [x] 프로모션 재고를 한도로, 프로모션이 최대로 적용될 수 있는 개수를 계산한다
- [x] 내가 가져온 수량에서, 증정받은 무료 수량을 계산한다



<br>


### 멤버십 할인

---

- [x]  프로모션을 적용하지 않는 나머지 가격에 대해 멤버십을 적용한다
    - 멤버십 회원은 프로모션 미적용 금액의 30%를 할인 받는다
    - 프로모션 적용 후 남은 금액에 대해 → 멤버십 할인을 적용한다
    - 멤버십 할인의 최대 한도는 8000원이다

<br>


### 영수증

---

- [x]  주문 결과를 계산한다
    - 영수증에서 주문총합을 계산한다
    - 행사 할인을 계산한다
    - 총 지불할 금액을 계산한다
<br>


- [x]  영수증은 **고객의 구매 내역**과 **할인**을 요약하여 출력한다
    - 영수증 항목
        - 구매 상품 내역 : 구매한 상품명, 수량, 가격
        - 증정 상품 내역: 프로모션에 따라 무료로 제공된 증정 상품의 목록
        - 금액 정보
            - 총구매액: 구매한 상품의 총 수량과 총 금액
            - 행사할인: 프로모션에 의해 할인된 금액
            - 멤버십할인: 멤버십에 의해 추가로 할인된 금액
            - 내실돈: 최종 결제 금액

```java
===========W 편의점=============
상품명		수량	금액
콜라		3 	3,000
에너지바 		5 	10,000
===========증	정=============
콜라		1
==============================
총구매액		8	13,000
행사할인			-1,000
멤버십할인			-3,000
내실돈			 9,000
```

<br>


### 입력

---

- [x] 구현에 필요한 상품 목록과 행사 목록을 파일 입출력을 통해 불러온다
    - 내용의 **형식**을 유지한다면 **값은 수정**할 수 있다

<br>


- [ ]  구매할 상품과 수량을 입력 받는다.
- 상품명, 수량은 **하이픈(-)**으로
- 개별 상품은 **대괄호[]**로 묶어
- **쉼표**로 ****구분한다
```java
[콜라-10],[사이다-3]
```

<br>


-[x] 추가 구매 여부를 입력 받는다.
    - Y: 고객이 다시 주문을 할 수 있게 한다
    - N: 구매를 종료한다



<br>

### 출력

---

- [ ]  productStorage 파일에서 값을 가져와 사용자에게 출력해준다
    - 환영 인사
    - 상품명, 가격, 재고, 프로모션 이름 안내
    - 만약 재고가 0개라면 `재고 없음`을 출력

```java
안녕하세요. W편의점입니다.
현재 보유하고 있는 상품입니다.

- 콜라 1,000원 10개 탄산2+1
- 콜라 1,000원 10개
- 사이다 1,000원 8개 탄산2+1
- 사이다 1,000원 7개
- 오렌지주스 1,800원 9개 MD추천상품
- 오렌지주스 1,800원 재고 없음
- 탄산수 1,200원 5개 탄산2+1
- 탄산수 1,200원 재고 없음
- 물 500원 10개
- 비타민워터 1,500원 6개
- 감자칩 1,500원 5개 반짝할인
- 감자칩 1,500원 5개
- 초코바 1,200원 5개 MD추천상품
- 초코바 1,200원 5개
- 에너지바 2,000원 5개
- 정식도시락 6,400원 8개
- 컵라면 1,700원 1개 MD추천상품
- 컵라면 1,700원 10개

구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])
```

- [ ]  프로모션 적용이 가능한 상품에 대해 고객이 해당 수량만큼 가져오지 않았을 경우, 혜택에 대한 안내 메시지를 출력한다.

```java
현재 {상품명}은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)
```

- [ ]  프로모션 재고가 부족하여 일부 수량을 프로모션 혜택 없이 결제해야 하는 경우, 일부 수량에 대해 정가로 결제할지 여부에 대한 안내 메시지를 출력한다.

```java
현재 {상품명} {수량}개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)
```

- [ ]  멤버십 할인 적용 여부를 확인하기 위해 안내 문구를 출력한다.

```java
멤버십 할인을 받으시겠습니까? (Y/N)
```

<br>


### 예외

---

- 구매할 상품과 수량 형식이 올바르지 않은 경우:
    - `[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.`
- 존재하지 않는 상품을 입력한 경우:
    - `[ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요.`
- 구매 수량이 재고 수량을 초과한 경우:
    - `[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.`
- 기타 잘못된 입력의 경우:
    - `[ERROR] 잘못된 입력입니다. 다시 입력해 주세요.`