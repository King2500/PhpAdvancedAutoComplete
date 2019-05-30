package net.king2500.plugins.PhpAdvancedAutoComplete;

import com.intellij.codeInsight.completion.CompletionConfidence;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.ThreeState;
import com.jetbrains.php.lang.psi.PhpFile;
import com.jetbrains.php.lang.psi.elements.ParameterList;
import com.jetbrains.php.lang.psi.elements.StringLiteralExpression;
import org.jetbrains.annotations.NotNull;

/**
 * @author Thomas Schulz <mail@king2500.net>
 */
public class PhpParameterStringCompletionConfidence extends CompletionConfidence {

    @NotNull
    @Override
    public ThreeState shouldSkipAutopopup(@NotNull PsiElement contextElement, @NotNull PsiFile psiFile, int offset) {

        if (!(psiFile instanceof PhpFile)) {
            return ThreeState.UNSURE;
        }

        PsiElement context = contextElement.getContext();
        if (!(context instanceof StringLiteralExpression)) {
            return ThreeState.UNSURE;
        }

//        // $test == "";
//        if(context.getParent() instanceof BinaryExpression) {
//            return ThreeState.NO;
//        }

        // $object->method("");
        PsiElement stringContext = context.getContext();
        if (stringContext instanceof ParameterList) {
            return ThreeState.NO;
        }

//        // $object->method(... array('foo'); array('bar' => 'foo') ...);
//        ArrayCreationExpression arrayCreationExpression = PhpElementsUtil.getCompletableArrayCreationElement(context);
//        if(arrayCreationExpression != null && arrayCreationExpression.getContext() instanceof ParameterList) {
//            return ThreeState.NO;
//        }

//        // $array['value']
//        if(PlatformPatterns.psiElement().withSuperParent(2, ArrayIndex.class).accepts(contextElement)) {
//            return ThreeState.NO;
//        }

        return ThreeState.UNSURE;
    }
}
