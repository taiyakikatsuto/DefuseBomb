import java.text.ParseException;					//Timerクラスの呼び出し
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

import java.awt.event.MouseListener;				//イベント処理を試みた残骸
import java.awt.event.MouseEvent;

import java.io.*;									//Scannerクラスの呼び出し

import java.util.Random;							//Randomクラスの呼び出し



public class DefuseBomb{

	public static void main(String[] args) {
			println("\n「3分後に地球が爆発します。地球を救いましょう」");							//オープニング
			timer();																				//タイマー始動後、ruteメソッドを呼び出し
			rute();
	}


	public static void rute() {																		//プレイ選択
		enter();	
		while (true) {
			String[] title = {"\n解除コードをゲット出来れば助かるらしい","1:解除コードを手に入れる 2:もっと楽な方法を探す 3:爆発するまで寝る"};
			println(title);

			int answer = input();
					
			switch (answer) {																	//入力によって処理分岐
				case 1:
					println("\nコード入力は全部で3回");
					game1();
					break;
				case 2:
					println("\n「横着しようとする奴は爆発します」");
					enter();
					exit();
				case 3:
					println("\nおやすみ世界");
					rute3();
				case -1:
					println("\n選択肢を入力してください");
					break;
				default:
					println("\n選択肢通りに答えなかったので爆発しました。");
					exit();
			}
		}
	}



	public static void game1() {																//数当てゲーム
		while(true)	{
			enter();
			String[] title = {line(),"はじめは0～255のうちのランダムな数字。ご丁寧にヒントもくれるらしい",
							"入力した数字が正解より大きいか小さいかを教えてくれる。",line()};
			println(title);
			enter();

			println(line() + "\n『GUESS THE NUMBER FOR 0～255』");
			Random rand = new Random();															//Randomクラスを使って0～255の数字を作成
			int i = rand.nextInt(256);

			for (int x = 0; x == x; x++) {														// 正解するまでくりかえす
				println(line());
				int number = input();

				if (i == number) {																	// 正解のチェック
					println("\n\n\n『 SUCCESS 』\n\n\n");
					game2();																			// 正解していたら繰り返しを抜けて次のゲームへ
				} else {
					println("ERROR");
					if (number == -1) {
						println("0～255の数字を入力してください");
					} else if (number > i) {																	// 大きい小さいのチェック
						println("THE NUMBER IS MORE SMALLER");
					} else {
						println("THE NUMBER IS MORE BIGGER");
					}
				}
			}
		}
	}



	public static void game2() {																//素数当てゲーム
		enter();
		String[] title = {line(),"次は素数2,3,5,7,11,13のうちのどれか3つ。",
							"\nこのゲームでは、推理パートと回答パートがある。\n推理パートで得たヒントをもとに、回答パートで3つとも数字を当てられればクリア。",
							"\n推理パート：「INPUT NUMBER?」の後に数字を入力する。ただし入力できるのは15以上の整数。\n入力した整数が答えの数字で割り切れるかどうかで、3つそれぞれが「TRUE」か「FALSE」を返す。",
							"\n回答パート：「0」を入力すると回答画面に移るので、正解だと思う素数を順番に答える。3つとも正答すればクリア。",line()};
		println(title);
		enter();

		int[] answer = new int[3];
		int[] input = new int[3];

		for (int i = 0; i < answer.length; i++) {													//ランダムな3つの素数を設定
		
			Random rand = new Random();
			answer[i] = rand.nextInt(14);

			while (isPrime(answer[i])) {
				answer[i] = rand.nextInt(14);
			}

			for (int j = i - 1; j >= 0; j--) {															//重複がない素数が出るまで繰り返す
				while (answer[i] == answer[j] || isPrime(answer[i])) {
					answer[i] = rand.nextInt(14);
				}
			}
		}

		while(true) {																				//ゲーム部分 クリアするまで繰り返す
			println(line() + "\nINPUT NUMBER?\n");
			int Q = input();
			if (Q == 0) {																	//0と入力で回答画面へ移動
				println(line());
				println("\nANSWER?\n");
				int count = 0;
				for (int i = 0; i < answer.length; i++) {											//回答部分
					System.out.print("\n" + (i+1) + "個目：");
					input[i] = input();
					if (input[i] == -1) {
						println("『2～13の数字を入力してください』");
					} else if (input[i] == answer[i]) {													//正解ならばカウントを増やし３カウントで次のゲームへ
						println("TRUE");
						count++;
						if (count == 3) {
							println("\n\n\n『 SUCCESS 』\n\n\n");
							game3();
						}
					} else {
						println("FALSE");
					}
				}
				println(line());

			} else if (Q >= 15){																	//答え推理部分 15以上の数字を受け付ける
				for (int i = 0; i < answer.length; i++) {
					System.out.print((i+1) + "個目：");													//入力した数字が答えの数字で割り切れるかどうかでtrueかfalseを表示する
					if (Q % answer[i] == 0) {
						System.out.print("TRUE  ");
					} else {
						System.out.print("FALSE  ");
					}
				}
			} else {																				//14以下の数字ではメッセージだけ表示しループ
				println("『15以上の数字を入力してください』");
			}
		}//while
	}//method



	public static void game3() {																	//4桁の数字当て
		enter();
		String title[] = {line(),"最後はランダムな4桁の数字。なお同じ数字は2度使われない",
							"まず各桁に0～9の数字を入力するとヒントが出る。\n桁も数字も合っていれば「HIT」、数字は合っているが桁が違う場合は「BLOW」がカウントされる。\n「HIT:4」、つまり正解の数字4つを順番通りに答えられればクリア。",line()};
		println(title);
		enter();
		println(line() + "\n『CODE BREAKER』");


		int[] answer = new int[4];																	//答えの配列、入力の配列を用意
		int[] input = new int[4];
		int hit = 0;
		int blow = 0;
		int count = 0;

		for (int i = 0; i < answer.length; i++) {													//ランダムな4桁を設定
			boolean flag = false;
			answer[i] = (int)(Math.random() * 10);

			do {																						//重複があればもう一度設定
				flag = false;
				for (int j = i - 1; j >= 0; j--) {
					if (answer[i] == answer[j]) {
						flag = true;
						answer[i] = (int)(Math.random() * 10);
					}
				}
			} while (flag == true);					
		}

		while (true) {																				//数字の重複がないことを確認したらゲーム部分へ
			hit = 0;
			blow = 0;
			String show = "\n\t";		
			count++;

			println(line() + "\n『" +count+ "th Challenge 』" );

			for (int i = 0; i < answer.length; i++) {												//回答部分
				System.out.print("  "+ (i+1) + "桁目：");
				int a = input();
				while (a < 0 || 9 < a) {
					println("0～9の数字を入力してください");
					a = input();
				}
				input[i] = a;
				show += input[i] + " ";
			}
			println(show + "\n");
		
			for (int i = 0; i < input.length; i++) {												//回答に合わせてhit、blow設定、表示
				for (int j = 0; j < answer.length; j++) {
					if (i == j && input[i] == answer[j]) {
						hit++;
					} else if (input[i] == answer[j]) {
						blow++;
					}
				}
			}

			println("\tHIT：" + hit + "\n\tBLOW：" + blow);									//hitとblowの数を表示

			if (hit == 4) {																			//hit４でクリア
				println("\n\n\n『 SUCCESS 』\n\n\n");
				clear();
			}
		}
	}



	public static void rute3() {														//無限ループがやりたいだけのルート
		enter();
		for(int i =0; i==i; i++){
			System.out.print("Zzzzzzz ");
		}
	}

	public static boolean isPrime(int n) {												//素数チェックメソッド
		if (n < 2) return true;
		if (n == 2) return false;
		if (n % 2 == 0) return true;

		for (int i = 3; i * i <= n; i += 2) {				//引数の整数に対し、素数ならばfalse,素数じゃなければtrueを返す
			if (n % i == 0) return true;
		}
		return false;
	}
	
	public static void timer() {														//タイマーメソッド
		Timer timer = new Timer(false);
		TimerTask task = new TimerTask() {
		@Override
			public void run() {
				println("\n\n地球は滅びた。モタモタしていたあなたのせいです。あーあ。");
				timer.cancel();
				exit();
			}
		};
		timer.schedule(task,180000);
	}

	public static void enter() {														//エンター入力メソッド
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try{
			print("⇒");
			String line = reader.readLine();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public static int input() {															//数値入力メソッド
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try{
			int i = Integer.parseInt(reader.readLine());
			return i;
		} catch (NumberFormatException e) {
			return -1;
		} catch (IOException e) {
			return -1;
		}
	}

	public static void print(String s) {												//出力メソッド
		System.out.print(s);
	}

	public static void println(String s) {												//出力メソッド
		System.out.println(s);
	}

	public static void println(String[] s) {
		for (int i = 0; i < s.length; i++ ) {
			System.out.println(s[i]);
		}
	}

	public static String line() {														//line出力メソッド
		return "\n-----------------------------------------------------------------------------------\n";
	}

	public static void clear() {														//クリアルート、エンディング
		enter();
		String[] ending = {"\n\t\t 地球は救われた。\n\n",line(),
							"\n\n\t\t   C L E A R  \n\n\n",line()};
		println(ending);
		System.exit(0);
	}

	public static void exit() {															//時間切れエンド
		println("\n  E N D  \n"+
" 　 　 　    　 . -‐ニ￣ニ‐- .\n"+
"　　　 　 　 ＿／　　　　　 　 ＼＿\n"+
"    　＝二￣　/　　　 　   　 　 ', ￣二＝\n"+
"　   　　 ￣7'' ―― ＿＿＿ ―― 戈￣\n"+
"―――　从,,i　; 　　 　　　 `.､ .尢r､――――――\n"+
"　　  　 　 /\\じ'jl|此ﾄ=ﾒ　i;_,,爻,,i| 刈ゞﾒ\n"+
"　　　　　　 ｀`‐ヾ:;!Iﾂﾞ 〃!iﾒﾄ辷-' ^\n"
);
		System.exit(0);
	}		
}