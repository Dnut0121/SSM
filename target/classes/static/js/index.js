document.addEventListener("DOMContentLoaded", function() {
    // 페이지 로딩 시 모든 상품을 먼저 로드
    loadProducts();

    // 카테고리 버튼 클릭 시 필터 처리
    let selectedCategory = ''; // 카테고리 상태를 저장하는 변수
    document.querySelectorAll('.filter-btn').forEach(button => {
        button.addEventListener('click', function() {
            const category = this.getAttribute('data-category');

            // 선택된 버튼을 처리
            if (this.classList.contains('selected')) {
                // 이미 선택된 버튼을 클릭하면 선택 해제
                this.classList.remove('selected');
                selectedCategory = ''; // 카테고리 해제
            } else {
                // 선택되지 않은 버튼을 클릭하면 다른 모든 버튼에서 'selected' 클래스 제거
                document.querySelectorAll('.filter-btn').forEach(btn => btn.classList.remove('selected'));
                // 클릭한 버튼에만 'selected' 클래스 추가
                this.classList.add('selected');
                selectedCategory = category; // 선택된 카테고리 저장
            }

            // 필터링된 상품 목록을 로드
            loadProducts(selectedCategory, document.getElementById('search-input').value);
        });
    });

    // 검색어 입력 시 처리
    document.getElementById('search-form').addEventListener('submit', function(event) {
        event.preventDefault();
        const searchQuery = document.getElementById('search-input').value;

        // 검색어와 현재 선택된 카테고리로 상품 로드
        loadProducts(selectedCategory, searchQuery);
    });
});

// 상품을 로드하는 함수 (카테고리와 검색어로 필터링)
function loadProducts(category = "", searchQuery = "") {
    // 서버 요청 전에 카테고리와 검색어 값 출력
    console.log(`Loading products with category: ${category} and search query: ${searchQuery}`);

    // Fetch API를 사용하여 서버에서 카테고리와 검색어로 상품을 가져옵니다.
    fetch(`/product-list?category=${category}&query=${searchQuery}`)
        .then(response => response.json())
        .then(data => {
            const productList = data.productList;
            const productListContainer = document.getElementById('product-list');
            const noProductsMessage = document.getElementById('no-products');

            // 기존 상품 목록 초기화
            productListContainer.innerHTML = '';

            if (productList.length > 0) {
                productList.forEach(product => {
                    const productDiv = document.createElement('div');
                    productDiv.classList.add('product');
                    productDiv.innerHTML = `
                        <h3>${product.name}</h3>
                        <p>브랜드: ${product.brand}</p>
                        <p>카테고리: ${product.category}</p>
                        <a href="/product-detail?id=${product.sSeq}" class="detail-link">상세보기</a>
                    `;
                    productListContainer.appendChild(productDiv);
                });

                // 검색 결과가 없을 경우 메시지 숨기기
                noProductsMessage.style.display = 'none';
            } else {
                // 상품이 없으면 "검색 결과가 없습니다" 메시지 표시
                noProductsMessage.style.display = 'block';
            }
        })
        .catch(error => {
            console.error('Error fetching data:', error);
        });
}
