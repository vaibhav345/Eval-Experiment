import org.lenskit.baseline.BaselineScorer
import org.lenskit.baseline.ItemMeanRatingItemScorer
import org.grouplens.lenskit.transform.normalize.BaselineSubtractingUserVectorNormalizer
import org.grouplens.lenskit.transform.normalize.UserVectorNormalizer
import org.lenskit.knn.NeighborhoodSize;
import org.lenskit.knn.user.NeighborFinder
import org.lenskit.knn.user.SnapshotNeighborFinder
import org.lenskit.knn.user.UserSimilarity
import org.lenskit.knn.user.UserUserItemScorer
import org.grouplens.lenskit.transform.normalize.*
import org.lenskit.baseline.*
import org.grouplens.lenskit.vectors.similarity.CosineVectorSimilarity
import org.grouplens.lenskit.vectors.similarity.VectorSimilarity



bind ItemScorer to UserUserItemScorer
bind (BaselineScorer, ItemScorer) to UserMeanItemScorer
bind (UserMeanBaseline, ItemScorer) to ItemMeanRatingItemScorer
set MeanDamping to 25
bind NeighborFinder to SnapshotNeighborFinder
at (UserUserItemScorer) {
    bind UserVectorNormalizer to DefaultUserVectorNormalizer
    within (UserVectorNormalizer) {
        bind VectorNormalizer to MeanVarianceNormalizer
    }
}
within (NeighborFinder) {
    bind UserVectorNormalizer to BaselineSubtractingUserVectorNormalizer
    within (UserVectorNormalizer) {
        bind (UserMeanBaseline, ItemScorer) to GlobalMeanRatingItemScorer
    }
}
within (UserSimilarity) {
    bind VectorSimilarity to CosineVectorSimilarity
}
set NeighborhoodSize to 30