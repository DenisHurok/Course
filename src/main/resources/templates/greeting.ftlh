    <#include "parts/security.ftlh">
    <#import "parts/common.ftlh" as c>
    <@c.page>
    <div class="background-container">
    <div class="background-overlay"></div>
    <div class="quicfun-text-container">
        <span class="quicfun-text">QuicFun</span>
            <div id="app" class="container">
                <card data-image="https://st3.depositphotos.com/3288047/16936/i/450/depositphotos_169364772-stock-photo-interior-of-the-cinema.jpg" link-url="/movies">
                    <h1 slot="header">Фильмы</h1>
                    <p slot="content">Здесь вы можете посмотреть подборк фильмов по вашим предпочтениям</p>
                </card>
                <card data-image="https://assets.allcafe.ru/k/places/canvas/640x360/pic/places/39394/717f74258b99eeb92e0d38e45bb5e078.jpeg" link-url="/places">
                    ">
                    <h1 slot="header">Заведения</h1>
                    <p slot="content">Содержимое</p>
                </card>
    </div>
        </div>
    </div>
    </div>
            </div>
    <!-- Vue.js из CDN -->
    <script src="https://cdn.jsdelivr.net/npm/vue@2"></script>
    <!-- Bootstrap JS из CDN (необязательно, если вы не используете компоненты Bootstrap) -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.1.0/js/bootstrap.bundle.min.js"></script>
    <!-- Ваш скрипт -->
    <script src="/js/app.js"></script>
            </@c.page>
