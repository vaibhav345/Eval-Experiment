import org.lenskit.baseline.BaselineScorer
import org.lenskit.baseline.ItemMeanRatingItemScorer
import org.lenskit.knn.item.ItemItemScorer
import org.grouplens.lenskit.transform.normalize.BaselineSubtractingUserVectorNormalizer
import org.grouplens.lenskit.transform.normalize.UserVectorNormalizer
import org.lenskit.knn.item.ModelSize;
import org.lenskit.knn.NeighborhoodSize;

bind ItemScorer to ItemItemScorer
bind UserVectorNormalizer to BaselineSubtractingUserVectorNormalizer
set ModelSize to 5000
set NeighborhoodSize to 20
within (UserVectorNormalizer) {
    bind (BaselineScorer, ItemScorer) to ItemMeanRatingItemScorer
}
