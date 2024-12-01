
  document.querySelector('.buy-button').addEventListener('click', async (event) => {
    event.preventDefault(); // 기본 동작 방지 (페이지 새로고침 방지)
    
    const productName = document.querySelector('.product-details h1').innerText; // 상품명
    const size = document.querySelector('#size-select').value; // 선택한 사이즈
    const color = document.querySelector('#color-select').value; // 선택한 색상

    // 구매 요청 데이터
    const purchaseData = {
      productName,
      size,
      color
    };

    try {
      const response = await fetch('/purchase', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(purchaseData)
      });

      if (response.ok) {
        alert('구매가 완료되었습니다.');
      } else {
        alert('구매 실패! 다시 시도해주세요.');
      }
    } catch (error) {
      console.error('Error:', error);
      alert('오류가 발생했습니다. 다시 시도해주세요.');
    }
  });

