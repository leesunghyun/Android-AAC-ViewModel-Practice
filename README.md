### Demo
![](https://im3.ezgif.com/tmp/ezgif-3-68bcf4f125.gif)

### 목적
각 화면에서 공통의 모델을 사용하는경우 데이터를 모두 동기화하여 동일한 View로 관리하기 위해서입니다.  
LiveData 및 ViewModel을 사용하기 전에는 RxBus를 통해서 백그라운드의 View를 갱신하였었습니다.  
백그라운드의 리스트의 데이터를 갱신하기 위해서는 변경된 Item을 리스트 검색을 통하여 찾은 후 갱신해야 하는 것.  
동일한 모델을 사용하는 화면이 많아질수록 유지보수 하기가 어려워진다는 문제가 있었습니다.

이 연습 프로젝트는 하나의 동일한 모델을 두개의 프라그먼트에서 공유하여 사용하도록 만들었습니다.
ArticleListFragment가 있으며, Article List의 Item을 클릭할 경우 ArticleDetailFragment로 이동합니다.
ArticleDetailFragment에서는 Article을 수정할 수 있으며 저장할 경우 백그라운드에 있는 ArticleListFragment의 UI도 최신 상태로 갱신됩니다.

### Single Activity Pattern 사용
ViewModel의 경우 Activity 단위로 생성하는것이 가능하므로 서로 다른 Activity간의 ViewModel을 공유하는것은 어렵다고 판단하여
Single Activity Pattern을 사용하며 모든 화면을 Fragment로 구성하였습니다.

### Branch
basic-livedata-viewmodel > Use LiveData, ViewModel.  
dagger > Use LiveData, ViewModel, Dagger2.  
