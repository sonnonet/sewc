public class Fragment4 extends Fragment {
    private static final String TAG = "Fragment4";
    Context context;
    OnTabItemSelectedListener listener;
    ScrollView scrollView;
    ImageView imageView;
    BitmapDrawable bitmap;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.context = context;

        if (context instanceof OnTabItemSelectedListener) {
            listener = (OnTabItemSelectedListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        if (context != null) {
            context = null;
            listener = null;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment4, container, false);
        initUI(rootView);
        return rootView;
    }


    private void initUI(ViewGroup rootView) {

        scrollView = rootView.findViewById(R.id.scrollView);
        imageView = rootView.findViewById(R.id.imageView);
        scrollView.setHorizontalScrollBarEnabled(true);

        Resources res = getResources();
        bitmap = (BitmapDrawable) res.getDrawable(R.drawable.image01);
        int bitmapWidth = bitmap.getIntrinsicWidth();
        int bitmapHeight = bitmap.getIntrinsicHeight();


        imageView.setImageDrawable(bitmap);
        imageView.getLayoutParams().width = bitmapWidth;
        imageView.getLayoutParams().height = bitmapHeight;
        Button button = rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Resources res = getResources();
                bitmap = (BitmapDrawable) res.getDrawable(R.drawable.image02);
                int bitmapWidth = bitmap.getIntrinsicWidth();
                int bitmapHeight = bitmap.getIntrinsicHeight();

                imageView.setImageDrawable(bitmap);
                imageView.getLayoutParams().width = bitmapWidth;
                imageView.getLayoutParams().height = bitmapHeight;
            }
        });

    }

}
